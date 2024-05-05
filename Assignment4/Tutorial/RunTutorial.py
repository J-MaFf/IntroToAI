import Tutorial1 as BaeysianNetwork

# use pysmile_license.py to activate the license
import pysmile_license as pylicense


class RunTutorial:
    def __init__(self):
        print("Starting RunTutorial...")
        self.license = pylicense
        # Check for tutorial1.xdsl file, if not found run Tutorial1
        try:
            with open("tutorial1.xdsl") as file:
                pass
        except FileNotFoundError:
            print("tutorial1.xdsl file not found. Creating tutorial1.xdsl...")
            self.BaeysNet = BaeysianNetwork.Tutorial1()
        else:
            print("tutorial1.xdsl file found, skipping creation.")

        # Run Tutorial2


# Run the tutorial
RunTutorial()
print("Tutorial complete.")
