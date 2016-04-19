package org.omidbiz.test;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

//import com.omid.framework.test.TestUtil;

/**
 * @author Omid Pourhadi
 *
 */
public class DeploymentResolver
{

    /**
     * simple deployment only add seam dependencies including hibernate etc ...
     * 
     * @return
     */
    public static WebArchive simpleSeamDeployment()
    {
        InputStream is = DeploymentResolver.class.getResourceAsStream("jboss-deployment-structure-test.txt");
        String deploymentContent = "";// TestUtil.readFile(is);
        //woodstox xml parser clean up
        deploymentContent = deploymentContent.replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", "");
        WebArchive war = ShrinkWrap.create(WebArchive.class, "framework-web-test.war");
        war.addAsWebInfResource(new StringAsset(deploymentContent), "jboss-deployment-structure.xml");
        war.addAsResource("seam.properties").addAsResource("components.properties");
        war.addAsWebInfResource("WEB-INF/components.xml", "components.xml").addAsWebInfResource("WEB-INF/pages.xml", "pages.xml");
        war.addAsWebInfResource("WEB-INF/web.xml", "web.xml");
        war.addAsResource("META-INF/persistence.xml");
        war.addAsResource("META-INF/ejb-jar.xml");
        File[] resolveAsFiles = DependencyResolvers.use(MavenDependencyResolver.class).includeDependenciesFromPom("pom_test.xml")
                .scope("compile").goOffline().resolveAsFiles();
        for (File file : resolveAsFiles)
        {
            System.out.println(file.getName());
        }
        war.addAsLibraries(resolveAsFiles);
        for (ArchivePath path : war.getContent().keySet())
        {
            if (path.get().contains("jboss-seam-ui"))
            {
                war.delete(path);
                break;
            }
        }
        System.out.println(war.toString());
        return war;
    }
    
    public static WebArchive securitySeamDeployment()
    {
        InputStream is = DeploymentResolver.class.getResourceAsStream("jboss-deployment-structure-test.txt");
        String deploymentContent = "";//TestUtil.readFile(is);
        //woodstox xml parser clean up
        deploymentContent = deploymentContent.replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", "");
        WebArchive war = ShrinkWrap.create(WebArchive.class, "framework-web-test.war");
        war.addAsWebInfResource(new StringAsset(deploymentContent), "jboss-deployment-structure.xml");
        war.addAsResource("seam.properties").addAsResource("components.properties");
        war.addAsWebInfResource("WEB-INF/security/components.xml", "components.xml").addAsWebInfResource("WEB-INF/pages.xml", "pages.xml");
        war.addAsWebInfResource("WEB-INF/web.xml", "web.xml");
        war.addAsResource("META-INF/persistence.xml");
        war.addAsResource("META-INF/ejb-jar.xml");
        File[] resolveAsFiles = DependencyResolvers.use(MavenDependencyResolver.class).includeDependenciesFromPom("pom_test.xml")
                .scope("compile").goOffline().resolveAsFiles();
        for (File file : resolveAsFiles)
        {
            System.out.println(file.getName());
        }
        war.addAsLibraries(resolveAsFiles);
        for (ArchivePath path : war.getContent().keySet())
        {
            if (path.get().contains("jboss-seam-ui"))
            {
                war.delete(path);
                break;
            }
        }
        System.out.println(war.toString());
        return war;
    }
    
    
    /**
     * contains seam and jbpm deployment
     * @return
     */
    public static WebArchive JbpmSeamDeployment()
    {
        InputStream is = DeploymentResolver.class.getResourceAsStream("jboss-deployment-structure-test.txt");
        String deploymentContent = "";//TestUtil.readFile(is);
        //woodstox xml parser clean up
        deploymentContent = deploymentContent.replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", "");
        WebArchive war = ShrinkWrap.create(WebArchive.class, "framework-web-test.war");
        war.addAsWebInfResource(new StringAsset(deploymentContent), "jboss-deployment-structure.xml");
        war.addAsResource("seam.properties").addAsResource("components.properties");
        war.addAsWebInfResource("WEB-INF/jbpm/components.xml", "components.xml").addAsWebInfResource("WEB-INF/pages.xml", "pages.xml");
        war.addAsWebInfResource("WEB-INF/web.xml", "web.xml");
        war.addAsResource("META-INF/persistence.xml");
        war.addAsResource("META-INF/ejb-jar.xml");
        war.addAsResource("WEB-INF/jbpm/testProcess.jpdl.xml", "testProcess.jpdl.xml");
        war.addAsResource("WEB-INF/jbpm/jbpm.cfg.xml", "jbpm.cfg.xml");
        war.addAsResource("WEB-INF/jbpm/hibernate.cfg.xml", "hibernate.cfg.xml");
        File[] resolveAsFiles = DependencyResolvers.use(MavenDependencyResolver.class).includeDependenciesFromPom("pom_test.xml")
                .scope("compile").goOffline().resolveAsFiles();
        war.addAsLibraries(resolveAsFiles);
        for (ArchivePath path : war.getContent().keySet())
        {
            if (path.get().contains("jboss-seam-ui"))
            {
                war.delete(path);
                break;
            }
        }
        traceArchive(war);
        System.out.println("Deployment Started");
        System.out.println(war.toString());       
        return war;
    }
    
    private static void traceArchive(Archive archive)
    {
        Map<ArchivePath, Node> content = archive.getContent();
        for (Map.Entry<ArchivePath, Node> item : content.entrySet())
        {
            System.out.println(item.getKey().get());
            System.out.println(item.getValue().getPath());
        }
    }
    
    
    /**
     * contains quartz and seam deployment
     * @return
     */
    public static WebArchive quartzSeamDeployment()
    {
        InputStream is = DeploymentResolver.class.getResourceAsStream("jboss-deployment-structure-test.txt");
        String deploymentContent = "";//TestUtil.readFile(is);
        //woodstox xml parser clean up
        deploymentContent = deploymentContent.replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", "");
        WebArchive war = ShrinkWrap.create(WebArchive.class, "framework-web-test.war");
        war.addAsWebInfResource(new StringAsset(deploymentContent), "jboss-deployment-structure.xml");
        war.addAsResource("seam.properties").addAsResource("components.properties").addAsResource("WEB-INF/quartz/seam.quartz.properties", "seam.quartz.properties");
        war.addAsWebInfResource("WEB-INF/quartz/components.xml", "components.xml").addAsWebInfResource("WEB-INF/pages.xml", "pages.xml");
        war.addAsWebInfResource("WEB-INF/web.xml", "web.xml");
        war.addAsResource("META-INF/persistence.xml");
        war.addAsResource("META-INF/ejb-jar.xml");
        File[] resolveAsFiles = DependencyResolvers.use(MavenDependencyResolver.class).includeDependenciesFromPom("pom_test.xml")
                .scope("compile").goOffline().resolveAsFiles();
        war.addAsLibraries(resolveAsFiles);
        for (ArchivePath path : war.getContent().keySet())
        {
            if (path.get().contains("jboss-seam-ui"))
            {
                war.delete(path);
                break;
            }
        }
        traceArchive(war);
        System.out.println("Deployment Started");
        System.out.println(war.toString());       
        return war;
    }
    
    
    /**
     * contains drools deployment
     * @return
     */
    public static WebArchive ruleSeamDeployment()
    {
        InputStream is = DeploymentResolver.class.getResourceAsStream("jboss-deployment-structure-test.txt");
        String deploymentContent = "";//TestUtil.readFile(is);
        //woodstox xml parser clean up
        deploymentContent = deploymentContent.replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", "");
        WebArchive war = ShrinkWrap.create(WebArchive.class, "framework-web-test.war");
        war.addAsWebInfResource(new StringAsset(deploymentContent), "jboss-deployment-structure.xml");
        war.addAsResource("seam.properties").addAsResource("components.properties");
        war.addAsWebInfResource("WEB-INF/drools/components.xml", "components.xml").addAsWebInfResource("WEB-INF/pages.xml", "pages.xml");
        war.addAsWebInfResource("WEB-INF/web.xml", "web.xml");
        war.addAsResource("META-INF/persistence.xml");
        war.addAsResource("META-INF/ejb-jar.xml");
        war.addAsResource("WEB-INF/drools/test.drl", "test.drl");
        File[] resolveAsFiles = DependencyResolvers.use(MavenDependencyResolver.class).includeDependenciesFromPom("pom_test.xml")
                .scope("compile").goOffline().resolveAsFiles();
        for (File file : resolveAsFiles)
        {
            System.out.println(file.getName());
        }
        war.addAsLibraries(resolveAsFiles);
        for (ArchivePath path : war.getContent().keySet())
        {
            if (path.get().contains("jboss-seam-ui"))
            {
                war.delete(path);
                break;
            }
        }
        System.out.println(war.toString());
        return war;
    }
    
    /**
     * contains every thing including hibernate, quartz, jbpm etc ..
     * @return
     */
    public static WebArchive defaultSeamDeployment()
    {
        InputStream is = DeploymentResolver.class.getResourceAsStream("jboss-deployment-structure-test.txt");
        String deploymentContent = "";//TestUtil.readFile(is);
        //woodstox xml parser clean up
        deploymentContent = deploymentContent.replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", "");
        WebArchive war = ShrinkWrap.create(WebArchive.class, "framework-web-test.war");
        war.addAsWebInfResource(new StringAsset(deploymentContent), "jboss-deployment-structure.xml");
        war.addAsResource("seam.properties").addAsResource("components.properties").addAsResource("WEB-INF/quartz/seam.quartz.properties","seam.quartz.properties");
        war.addAsWebInfResource("WEB-INF/seam/components.xml", "components.xml").addAsWebInfResource("WEB-INF/pages.xml", "pages.xml");
        war.addAsWebInfResource("WEB-INF/web.xml", "web.xml");
        war.addAsResource("META-INF/persistence.xml");
        war.addAsResource("META-INF/ejb-jar.xml");
        war.addAsResource("WEB-INF/jbpm/testProcess.jpdl.xml", "testProcess.jpdl.xml");
        war.addAsResource("WEB-INF/jbpm/jbpm.cfg.xml", "jbpm.cfg.xml");
        war.addAsResource("WEB-INF/jbpm/hibernate.cfg.xml", "hibernate.cfg.xml");
        File[] resolveAsFiles = DependencyResolvers.use(MavenDependencyResolver.class).includeDependenciesFromPom("pom_test.xml")
                .scope("compile").goOffline().resolveAsFiles();
        war.addAsLibraries(resolveAsFiles);
        for (ArchivePath path : war.getContent().keySet())
        {
            if (path.get().contains("jboss-seam-ui"))
            {
                war.delete(path);
                break;
            }
        }
        traceArchive(war);
        System.out.println("Deployment Started");
        System.out.println(war.toString());       
        return war;
    }

    private static final String LOCAL_MAVEN_REPO = System.getProperty("maven.repo.local") != null ? System.getProperty("maven.repo.local")
            : (System.getProperty("user.home") + File.separatorChar + ".m2" + File.separatorChar + "repository");

    public static File resolve(final String groupId, final String artifactId, final String version, final String classifier)
    {
        return new File(LOCAL_MAVEN_REPO + File.separatorChar + groupId.replace(".", File.separator) + File.separatorChar + artifactId
                + File.separatorChar + version + File.separatorChar + artifactId + "-" + version
                + (classifier != null && classifier.trim().length() > 0 ? ("-" + classifier) : "") + ".jar");
    }
}
