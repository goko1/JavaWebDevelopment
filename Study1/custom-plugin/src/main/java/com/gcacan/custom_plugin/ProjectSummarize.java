package com.gcacan.custom_plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.maven.model.Plugin;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Developer;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

//project-summarize is the ame of the goal.
@Mojo(name="summarize", defaultPhase = LifecyclePhase.COMPILE)
public class ProjectSummarize extends AbstractMojo {

	@Parameter(defaultValue = "${project}", required=true, readonly=true)
	private MavenProject project;
	
	private File outputDirectory;
	
	@Parameter(property="outputFile", defaultValue = "out.txt", required = true)
	private String fileName;
	
	
	public void execute() throws MojoExecutionException, MojoFailureException {
	
		String groupId = project.getGroupId();
		String artifactId = project.getArtifactId();
		String version = project.getVersion();
		
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Project Info : " + groupId + "." + artifactId + "." + version + "\n");
		
		strBuilder.append("Developers : \n");
		List<Developer> developers = project.getDevelopers();
		
		int index = 0;
		for(Developer i : developers) {
			strBuilder.append("Developer " + (++index) + "Name : " + i.getName() + "\n");
		}
		
		String releaseDate = project.getProperties().getProperty("release.date");
		strBuilder.append("Release Date : " + releaseDate + "\n");
		
		strBuilder.append("Dependencies \n");
		List<Dependency> dependencies = project.getDependencies();
		for(Dependency d : dependencies) {
			strBuilder.append("Dependency : " + d.getGroupId() + "." + d.getArtifactId() + "\n");
		}
		
		strBuilder.append("Plugins \n");
		List<Plugin> plugins = project.getBuildPlugins();
		for(Plugin p : plugins) {
			strBuilder.append("Plugin : " + p.getArtifactId() + "\n");
		}
		
		try {
			String path = fileName;
			String target = "target";
			File f = new File(target);
			File outputFile = new File(f,path);
			
			FileWriter writer = new FileWriter(outputFile);
			
			writer.write(strBuilder.toString());
			
			writer.close();
			
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		
		
	}

}
