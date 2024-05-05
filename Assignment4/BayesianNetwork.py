import pysmile as ps
class BayesianNetwork:
    """
    Represents a Bayesian Network.

    Attributes:
        nodes (list): A list of Node objects representing the nodes in the network.
        node_dict (dict): A dictionary mapping node names to Node objects.
        node_parents (dict): A dictionary mapping node names to lists of parent node names.
        node_children (dict): A dictionary mapping node names to lists of child node names.
        node_values (dict): A dictionary mapping node names to lists of possible values.
        node_cpt (dict): A dictionary mapping node names to conditional probability tables.
    """

    def __init__(self, nodes):
        self.nodes = nodes
        self.node_dict = {node.name: node for node in nodes}
        self.node_parents = {node.name: node.parents for node in nodes}
        self.node_children = {node.name: node.children for node in nodes}
        self.node_values = {node.name: node.values for node in nodes}
        self.node_cpt = {node.name: node.cpt for node in nodes}
    
