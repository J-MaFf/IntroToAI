import pysmile

import Assignment4.Tutorial.pysmile_license as pysmile_license


# Tutorial1 creates a simple network with three nodes,

# then writes its content as XDSL file to disk.


class Tutorial1:
    def __init__(self):
        """
        Initializes the Tutorial1 class.

        This method creates a Bayesian network using the PySmile library and sets up the nodes and their definitions.
        The network represents a simple example of a probabilistic model with three nodes: Economy, Success, and Forecast.
        The nodes are connected by arcs to represent dependencies between them.

        Returns:
            None
        """
        print("Starting Tutorial1...")

        net = pysmile.Network()  # creating a network object allows us to create nodes and arcs

        e = self.create_cpt_node(
            net, "Economy", "State of the economy", ["Up", "Flat", "Down"], 160, 40
        )

        s = self.create_cpt_node(
            net, "Success", "Success of the venture", ["Success", "Failure"], 60, 40
        )

        f = self.create_cpt_node(
            net,  # (pysmile.Network): The network to add the node to.
            "Forecast"  # id (int): The ID of the node.
            "Expert forecast",  # name (str): The name of the node.
            [
                "Good",
                "Moderate",  # outcomes (list): A list of outcome names for the node.
                "Poor",
            ],
            110,  # x_pos (int): The x-coordinate position of the node. This is useful for visualization.
            140,  # y_pos (int): The y-coordinate position of the node. This is useful for visualization.
        )

        net.add_arc(e, s)  # Economy -> Success
        net.add_arc(s, f)  # Success -> Forecast
        net.add_arc(  # Economy -> Forecast. You can also use node names
            "Economy",
            "Forecast",
        )
        # The following code sets the probabilities for each node
        economyDef = [0.2, 0.7, 0.1]  # P(Economy=Up)  # P(Economy=Flat)  # P(Economy=Down)
        net.set_node_definition(e, economyDef)

        successDef = [
            0.3,  # P(Success=Success|Economy=Up)
            0.7,  # P(Success=Failure|Economy=Up)
            0.2,  # P(Success=Success|Economy=Flat)
            0.8,  # P(Success=Failure|Economy=Flat)
            0.1,  # P(Success=Success|Economy=Down)
            0.9,  # P(Success=Failure|Economy=Down)
        ]
        net.set_node_definition(s, successDef)

        forecastDef = [
            0.70,  # P(Forecast=Good|Success=Success,Economy=Up)
            0.29,  # P(Forecast=Moderate|Success=Success,Economy=Up)
            0.01,  # P(Forecast=Poor|Success=Success,Economy=Up)
            0.65,  # P(Forecast=Good|Success=Success,Economy=Flat)
            0.30,  # P(Forecast=Moderate|Success=Success,Economy=Flat)
            0.05,  # P(Forecast=Poor|Success=Success,Economy=Flat)
            0.60,  # P(Forecast=Good|Success=Success,Economy=Down)
            0.30,  # P(Forecast=Moderate|Success=Success,Economy=Down)
            0.10,  # P(Forecast=Poor|Success=Success,Economy=Down)
            0.15,  # P(Forecast=Good|Success=Failure,Economy=Up)
            0.30,  # P(Forecast=Moderate|Success=Failure,Economy=Up)
            0.55,  # P(Forecast=Poor|Success=Failure,Economy=Up)
            0.10,  # P(Forecast=Good|Success=Failure,Economy=Flat)
            0.30,  # P(Forecast=Moderate|Success=Failure,Economy=Flat)
            0.60,  # P(Forecast=Poor|Success=Failure,Economy=Flat)
            0.05,  # P(Forecast=Good|Success=Failure,Economy=Down)
            0.25,  # P(Forecast=Moderate|Success=Failure,Economy=Down)
            0.70,  # P(Forecast=Poor|Success=Failure,Economy=Down)
        ]
        net.set_node_definition(f, forecastDef)

        net.write_file(
            "tutorial1.xdsl"
        )  # This writes the network to a file. You can open this file with GeNIe

        print("Tutorial1 complete: Network written to tutorial1.xdsl")


def create_cpt_node(self, net, id, name, outcomes, x_pos, y_pos):
    """
    Create a CPT (Conditional Probability Table) node in the network.

    Args:
        net (pysmile.Network): The network to add the node to.
        id (int): The ID of the node.
        name (str): The name of the node.
        outcomes (list): A list of outcome names for the node.
        x_pos (int): The x-coordinate position of the node.
        y_pos (int): The y-coordinate position of the node.

    Returns:
        int: The handle of the created node.

    """
    handle = net.add_node(
        pysmile.NodeType.CPT, id
    )  # It is called handle because it is a reference to the node in the network

    net.set_node_name(handle, name)

    net.set_node_position(
        handle, x_pos, y_pos, 85, 55
    )  # 85 and 55 are the width and height of the node

    initial_outcome_count = net.get_outcome_count(
        handle
    )  # This is the number of outcomes the node has

    for i in range(0, initial_outcome_count):
        net.set_outcome_id(handle, i, outcomes[i])  # This sets the name of the outcome

    for i in range(initial_outcome_count, len(outcomes)):
        net.add_outcome(handle, outcomes[i])  # This adds a new outcome to the node

    return handle
