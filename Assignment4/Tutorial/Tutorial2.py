import pysmile


# Tutorial2 loads the XDSL file created by Tutorial1,

# then performs the series of inference calls,

# changing evidence each time.


class Tutorial2:
    """
    This class represents Tutorial2 and demonstrates the usage of a network created by Tutorial1.
    """

    def __init__(self):
        """
        Initializes Tutorial2 and performs various operations on the network.
        """

        print("Starting Tutorial2...")

        net = pysmile.Network()

        # load the network created by Tutorial1

        net.read_file("tutorial1.xdsl")

        print("Posteriors with no evidence set:")

        net.update_beliefs()

        self.print_all_posteriors(net)

        print("Setting Forecast=Good.")

        self.change_evidence_and_update(net, "Forecast", "Good")

        print("Adding Economy=Up.")

        self.change_evidence_and_update(net, "Economy", "Up")

        print("Changing Forecast to Poor, keeping Economy=Up.")

        self.change_evidence_and_update(net, "Forecast", "Poor")

        print("Removing evidence from Economy, keeping Forecast=Poor.")

        self.change_evidence_and_update(net, "Economy", None)

        print("Tutorial2 complete.")

    def print_posteriors(self, net, node_handle):
        """
        Prints the posteriors of a given node in the network.
        Posteriors are the probabilities of each outcome of the node given the evidence.

        Args:
            net: The network object.
            node_handle: The handle of the node.

        Returns:
            None
        """

        node_id = net.get_node_id(node_handle)

        if net.is_evidence(node_handle):
            print(f"{node_id} has evidence set ({net.get_evidence_id(node_handle)})")
        else:
            posteriors = net.get_node_value(node_handle)
            for i in range(0, len(posteriors)):
                print(f"P({node_id}={net.get_outcome_id(node_handle, i)})={posteriors[i]}")

    def print_all_posteriors(self, net):
        """
        Prints the posteriors of all nodes in the network.

        Args:
            net: The network object.

        Returns:
            None
        """

        for handle in net.get_all_nodes():
            self.print_posteriors(net, handle)

    def change_evidence_and_update(self, net, node_id, outcome_id):
        """
        Changes the evidence of a node in the network and updates the beliefs.

        Args:
            net: The network object.
            node_id: The ID of the node.
            outcome_id: The ID of the outcome.

        Returns:
            None
        """

        if outcome_id is not None:
            net.set_evidence(node_id, outcome_id)
        else:
            net.clear_evidence(node_id)

        net.update_beliefs()

        self.print_all_posteriors(net)

        print()