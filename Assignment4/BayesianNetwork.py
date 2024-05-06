import pysmile as ps


class BayesianNetwork:
    # Thes class will use pysmile to create a Bayesian Network
    def __init__(self, name):
        """
        Initializes the BayesianNetwork class.

        This method creates a Bayesian network using the PySmile library and sets up the nodes and their definitions.
        The network represents a simple example of a probabilistic model with three nodes: Economy, Success, and Forecast.
        The nodes are connected by arcs to represent dependencies between them.

        Args:
            name (str): The name of the network.

        Returns:
            None
        """
        self.net = ps.Network()
        self.name = name
        self.createNodes()
        self.createArcs()

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
        handle = net.add_node(  # It is called handle because it is a reference to the node in the network
            ps.NodeType.CPT,
            id,  # This creates a node with a Conditional Probability Table (CPT)
        )

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

    def createNodes(self):
        """
        Creates the nodes for the Bayesian network.

        Returns:
            None
        """
        # Create Geographical_area node
        self.geographialAreaNode = self.create_cpt_node(
            self.net, 0, "Geographical Area", ["Centre", "North", "South islands"], 50, 50
        )

        # Create Education node
        self.educationNode = self.create_cpt_node(
            self.net, 1, "Education", ["High", "Low"], 100, 50
        )

        # Create Allergy node
        self.allergyNode = self.create_cpt_node(self.net, 2, "Allergy", ["No", "Yes"], 150, 50)

        # Create Smoke node
        self.smokeNode = self.create_cpt_node(self.net, 3, "Smoke", ["No", "Yes"], 200, 50)

        # Create Sedentary node
        self.sedentaryNode = self.create_cpt_node(self.net, 4, "Sedentary", ["No", "Yes"], 250, 50)

        # Create Age node
        self.ageNode = self.create_cpt_node(self.net, 5, "Age", ["Adult", "Old", "Young"], 300, 50)

        # Create Sex node
        self.sexNode = self.create_cpt_node(self.net, 6, "Sex", ["Female", "Male"], 350, 50)

        # Create Urbanization node
        self.urbanizationNode = self.create_cpt_node(
            self.net, 7, "Urbanization", ["High", "Low", "Medium"], 400, 50
        )

        # Create Prior for Asthma node
        self.asthmaNode = self.create_cpt_node(self.net, 8, "Asthma", ["No", "Yes"], 450, 50)

    def createArcs(self):
        """
        Creates arcs between the asthma node and other nodes in the Bayesian network.

        This method adds arcs from the asthma node to the geographical area, education,
        allergy, smoke, sedentary, age, sex, and urbanization nodes in the network.

        Returns:
            None
        """
        self.net.add_arc(self.asthmaNode, self.geographialAreaNode)  # Asthma -> Geographical Area
        self.net.add_arc(self.asthmaNode, self.educationNode)  # Asthma -> Education
        self.net.add_arc(self.asthmaNode, self.allergyNode)  # Asthma -> Allergy
        self.net.add_arc(self.asthmaNode, self.smokeNode)  # Asthma -> Smoke
        self.net.add_arc(self.asthmaNode, self.sedentaryNode)  # Asthma -> Sedentary
        self.net.add_arc(self.asthmaNode, self.ageNode)  # Asthma -> Age
        self.net.add_arc(self.asthmaNode, self.sexNode)  # Asthma -> Sex
        self.net.add_arc(self.asthmaNode, self.urbanizationNode)  # Asthma -> Urbanization
