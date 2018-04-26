var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":62,"id":785,"methods":[{"el":22,"sc":5,"sl":17},{"el":28,"sc":5,"sl":24},{"el":32,"sc":5,"sl":30},{"el":36,"sc":5,"sl":34},{"el":40,"sc":5,"sl":38},{"el":44,"sc":5,"sl":42},{"el":48,"sc":5,"sl":46},{"el":52,"sc":5,"sl":50},{"el":60,"sc":5,"sl":54}],"name":"Die","sl":10}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":54}],"name":"testSetNum","pass":true,"statements":[{"sl":55},{"sl":56},{"sl":59}]},"test_2":{"methods":[{"sl":30},{"sl":34},{"sl":38},{"sl":42},{"sl":46}],"name":"testGettersAndSetters","pass":false,"statements":[{"sl":31},{"sl":35},{"sl":39},{"sl":43},{"sl":47}]},"test_24":{"methods":[{"sl":30},{"sl":34},{"sl":38},{"sl":42},{"sl":46}],"name":"testGettersAndSetters","pass":false,"statements":[{"sl":31},{"sl":35},{"sl":39},{"sl":43},{"sl":47}]},"test_29":{"methods":[{"sl":54}],"name":"testSetNum","pass":true,"statements":[{"sl":55},{"sl":56},{"sl":59}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [24, 2], [24, 2], [], [], [24, 2], [24, 2], [], [], [24, 2], [24, 2], [], [], [24, 2], [24, 2], [], [], [24, 2], [24, 2], [], [], [], [], [], [], [1, 29], [1, 29], [1, 29], [], [], [1, 29], [], [], []]
