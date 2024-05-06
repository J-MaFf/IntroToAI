import pysmile


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
        self.net = pysmile.Network()
        self.name = name
        self.createNodes()
        self.createArcs()
        self.setNodeDefinition()
        # 1.	What is the probability of a young, smoker, male to get ashma?
        # 2. What is the probability of an old, female, non-smoker, who lives in the north to have ashma?

    def createCptNode(self, net, id, name, outcomes, x_pos, y_pos):
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
            pysmile.NodeType.CPT,
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
        self.geographialAreaNode = self.createCptNode(
            self.net, 0, "Geographical Area", ["Centre", "North", "South islands"], 50, 50
        )

        # Create Education node
        self.educationNode = self.createCptNode(self.net, 1, "Education", ["High", "Low"], 100, 50)

        # Create Allergy node
        self.allergyNode = self.createCptNode(self.net, 2, "Allergy", ["No", "Yes"], 150, 50)

        # Create Smoke node
        self.smokeNode = self.createCptNode(self.net, 3, "Smoke", ["No", "Yes"], 200, 50)

        # Create Sedentary node
        self.sedentaryNode = self.createCptNode(self.net, 4, "Sedentary", ["No", "Yes"], 250, 50)

        # Create Age node
        self.ageNode = self.createCptNode(self.net, 5, "Age", ["Adult", "Old", "Young"], 300, 50)

        # Create Sex node
        self.sexNode = self.createCptNode(self.net, 6, "Sex", ["Female", "Male"], 350, 50)

        # Create Urbanization node
        self.urbanizationNode = self.createCptNode(
            self.net, 7, "Urbanization", ["High", "Low", "Medium"], 400, 50
        )

        # Create Prior for Asthma node
        self.asthmaNode = self.createCptNode(self.net, 8, "Asthma", ["No", "Yes"], 450, 50)

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

    def setNodeDefinition(self):
        geographicalAreaDef = [
            0.18690401,  # P(Geographical Area=Centre|Asthma=No)
            0.44310235,  # P(Geographical Area=North|Asthma=No)
            0.36999364,  # P(Geographical Area=South islands|Asthma=No)
            0.20558376,  # P(Geographical Area=Centre|Asthma=Yes)
            0.39509306,  # P(Geographical Area=North|Asthma=Yes)
            0.39932318,  # P(Geographical Area=South islands|Asthma=Yes)
        ]
        self.net.set_node_definition(self.geographialAreaNode, geographicalAreaDef)

        educationDef = [
            0.50095359,  # P(Education=High|Asthma=No)
            0.49904641,  # P(Education=Low|Asthma=No)
            0.40270728,  # P(Education=High|Asthma=Yes)
            0.59729272,  # P(Education=Low|Asthma=Yes)
        ]
        self.net.set_node_definition(self.educationNode, educationDef)

        allergyDef = [
            0.87857597,  # P(Allergy=No|Asthma=No)
            0.12142403,  # P(Allergy=Yes|Asthma=No)
            0.5177665,  # P(Allergy=No|Asthma=Yes)
            0.4822335,  # P(Allergy=Yes|Asthma=Yes)
        ]
        self.net.set_node_definition(self.allergyNode, allergyDef)

        smokeDef = [
            0.84678957,  # P(Smoke=No|Asthma=No)
            0.15321043,  # P(Smoke=Yes|Asthma=No)
            0.83671743,  # P(Smoke=No|Asthma=Yes)
            0.16328257,  # P(Smoke=Yes|Asthma=Yes)
        ]
        self.net.set_node_definition(self.smokeNode, smokeDef)

        sedentaryDef = [
            0.24920534,  # P(Sedentary=No|Asthma=No)
            0.75079466,  # P(Sedentary=Yes|Asthma=No)
            0.21150592,  # P(Sedentary=No|Asthma=Yes)
            0.78849408,  # P(Sedentary=Yes|Asthma=Yes)
        ]
        self.net.set_node_definition(self.sedentaryNode, sedentaryDef)

        ageDef = [
            0.54609027,  # P(Age=Adult|Asthma=No)
            0.33121424,  # P(Age=Old|Asthma=No)
            0.12269549,  # P(Age=Young|Asthma=No)
            0.45939086,  # P(Age=Adult|Asthma=Yes)
            0.43824027,  # P(Age=Old|Asthma=Yes)
            0.10236887,  # P(Age=Young|Asthma=Yes)
        ]
        self.net.set_node_definition(self.ageNode, ageDef)

        sexDef = [
            0.52765416,
            0.47234584,
            0.54060914,
            0.45939086,
        ]
        self.net.set_node_definition(self.sexNode, sexDef)

        urbanizationDef = [
            0.30769231,  # P(Urbanization=High|Asthma=No)
            0.27908455,  # P(Urbanization=Low|Asthma=No)
            0.41322314,  # P(Urbanization=Medium|Asthma=No)
            0.3248731,  # P(Urbanization=High|Asthma=Yes)
            0.27580372,  # P(Urbanization=Low|Asthma=Yes)
            0.39932318,  # P(Urbanization=Medium|Asthma=Yes)
        ]
        self.net.set_node_definition(self.urbanizationNode, urbanizationDef)

        asthmaDef = [0.57096189, 0.42903811]  # P(Asthma=No), P(Asthma=Yes)
        self.net.set_node_definition(self.asthmaNode, asthmaDef)
