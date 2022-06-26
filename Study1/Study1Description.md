### Study1
Develop a custom maven plugin and use that plugin in a maven build.

The custom plugin should require these :
1-Getting project details: groupId, artifactId, dependencies, developers and release.date(as a property) 
2-Taking a parameter as name as outputFile, writing details in this file (save to the target folder.)

Sample output:
Project info : groupId.artifactId.versiyon

Developers : 
Developer 1 Name : name 
Developer 2 Name : name 

Developer ...
Release Date : releaseDate 
Dependencies 
Dependency : groupId.artifactId 
Dependency : groupId.artifactId 
Dependency : ....

Plugins 
Plugin : artifactId 
Plugin : artifactId
Plugin ...

3-Use maven clean install phases to save this plugin in the local repository

4-Use this plugin in a maven project.

Note: 
To develop the plugin we need to use maven archetype, filter archetypes with org.apache.maven and choose mojo archetype.
https://www.baeldung.com/maven-plugin