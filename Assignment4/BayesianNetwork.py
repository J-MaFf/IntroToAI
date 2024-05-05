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
        print(
            "This is a very long line of text that is specifically designed to exceed the one hundred character limit that we have set for our Python formatter."
        )
