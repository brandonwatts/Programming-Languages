from operator import itemgetter
from itertools import groupby

class GoogleMapReduce:
    
    # Variable to hold the output from googleMap before it is grouped
    output = []
    
    # Varaible to hold the output from googleMap before it is grouped
    groupedOutput = []

    def googleMap(self,input2Dtuple, keyIndices, valueIndices, googleMapper):
        for inputTuple in input2Dtuple:
            for key in keyIndices:
                valueList = [] # Create a temporary array 
                if(len(valueIndices) == 1): # There is just a single value so just pass it
                    valueList = inputTuple[1]
                else: # There are multiple values so we need to store them in a list
                    for value in valueIndices:
                        valueList.append(inputTuple[value])
            self.output += (googleMapper(inputTuple[key], valueList))
            
        # Group the tuples    
        groupings = sorted(self.output, key=itemgetter(0))
        for key, group in groupby(groupings, lambda x: x[0]):
            localList = []
            for outputTuple in group:
                localList.append(outputTuple[1])
            self.groupedOutput.append((key, localList))

    def googleReduce(self, googleReducer):
        localList = []
        for groupedTuple in self.groupedOutput:
            localList += googleReducer(groupedTuple[0],groupedTuple[1])
        return localList

    def getMappedCopy(self):
        from copy import deepcopy
        copy = deepcopy(self.output)
        return copy

    def getGroupedByCopy(self):
        from copy import deepcopy
        copy = deepcopy(self.groupedOutput)
        return copy