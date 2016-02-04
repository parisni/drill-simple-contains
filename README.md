# Simple Contains Function for Apache Drill

This functions regex matches for drill


## How to Compile Install

Clone and compile.

```

git clone https://github.com/parisni/drill-simple-contains.git

cd drill-simple-contains

mvn package

```


Copy the jar files from your functions into the 3rdparty directory in the Drill distro

```
cp drill-simple-contains/target/*.jar apache-drill-X.X.X/jars/3rdparty
```

Now run drill and test the results

```
$ cd apache-drill-X.X.X/
$ bin/drill-embedded
0: jdbc:drill:zk=local>
SELECT * FROM cp.`employee.json` WHERE contains(first_name,'She.*');
first_name |
-----------|
Sheri      |
Sheila     |
Shelby     |
Shelley    |
Shelly     |
Sheila     |
```


