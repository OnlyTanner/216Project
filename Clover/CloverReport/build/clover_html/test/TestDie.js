var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":66,"id":1526,"methods":[{"el":20,"sc":5,"sl":17},{"el":25,"sc":5,"sl":22},{"el":30,"sc":5,"sl":27},{"el":34,"sc":5,"sl":32},{"el":44,"sc":5,"sl":36},{"el":65,"sc":5,"sl":46}],"name":"TestDie","sl":14}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":46}],"name":"testSetNum","pass":true,"statements":[{"sl":48},{"sl":49},{"sl":50},{"sl":56},{"sl":57},{"sl":59},{"sl":60},{"sl":62},{"sl":63}]},"test_2":{"methods":[{"sl":36}],"name":"testGettersAndSetters","pass":false,"statements":[{"sl":38},{"sl":39},{"sl":40},{"sl":41},{"sl":42}]},"test_24":{"methods":[{"sl":36}],"name":"testGettersAndSetters","pass":false,"statements":[{"sl":38},{"sl":39},{"sl":40},{"sl":41},{"sl":42}]},"test_29":{"methods":[{"sl":46}],"name":"testSetNum","pass":true,"statements":[{"sl":48},{"sl":49},{"sl":50},{"sl":56},{"sl":57},{"sl":59},{"sl":60},{"sl":62},{"sl":63}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [24, 2], [], [24, 2], [24, 2], [24, 2], [24, 2], [24, 2], [], [], [], [1, 29], [], [1, 29], [1, 29], [1, 29], [], [], [], [], [], [1, 29], [1, 29], [], [1, 29], [1, 29], [], [1, 29], [1, 29], [], [], []]
