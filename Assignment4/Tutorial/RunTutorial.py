import Tutorial1 as BaeysianNetwork

import pysmile_license


class RunTutorial:
    def __init__(self):
        self.BaeysNet = BaeysianNetwork.Tutorial1()

    def run_tutorial1(self):  # Tutorail1 creates a Bayesian Network
        self.BaeysNet.create_tutorial1()

    def run_tutorial2(self):  # Tutorial2 uses the Bayesian Network created by Tutorial1
        self.BaeysNet.create_tutorial2()  # and performs various operations on the network.

    def run(self):
        self.run_tutorial1()
        # self.run_tutorial2()


run = RunTutorial().BaeysNet.run_tutorial1()
