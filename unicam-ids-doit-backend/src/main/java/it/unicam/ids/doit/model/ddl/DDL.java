package it.unicam.ids.doit.model.ddl;

import java.time.Instant;
import java.util.EnumSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * Utilità per generare il DDL per la creazione delle tabelle, da incollare nel file
 * /unicam-ids-doit-backend/src/main/resources/it/unicam/ids/doit/model/create.sql.
 * 
 * @author Giulio Quaresima (giulio.quaresima--at--gmail.com, giulio.quaresima--at--unipg.it, giulio.quaresima--at--studenti.unicam.it)
 */
public class DDL
{

	public static void main(String[] args) throws ClassNotFoundException
	{
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySetting(Environment.DIALECT, H2Dialect.class)
				.applySetting(Environment.PHYSICAL_NAMING_STRATEGY, SpringPhysicalNamingStrategy.class)
				.build();
				
		ClassPathScanningCandidateComponentProvider classPathScanningCandidateComponentProvider = new ClassPathScanningCandidateComponentProvider(false);
		classPathScanningCandidateComponentProvider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
		classPathScanningCandidateComponentProvider.addIncludeFilter(new AnnotationTypeFilter(MappedSuperclass.class));
		
		MetadataSources metadataSources = new MetadataSources(serviceRegistry);
		String[] packageNames = {"it.unicam.ids.doit.model"};
		for (String packageName : packageNames)
		{
			metadataSources.addPackage(packageName);
			Set<BeanDefinition> beanDefinitions = classPathScanningCandidateComponentProvider.findCandidateComponents(packageName);
			for (BeanDefinition beanDefinition : beanDefinitions)
			{
				System.out.println(beanDefinition.getBeanClassName());
				metadataSources.addAnnotatedClass(Class.forName(beanDefinition.getBeanClassName()));
			}
		}
		
		Metadata metadata = metadataSources.buildMetadata();

		SchemaExport schemaExport = new SchemaExport();
		schemaExport.setFormat(true);
		schemaExport.setDelimiter(";");
		System.out.println("---------------------------------------------------");
		System.out.println("-- Generated by it.unicam.ids.doit.model.ddl.DDL --");
		System.out.printf("-- at %s                --\n", Instant.now());
		System.out.println("---------------------------------------------------");
		System.out.println();
		schemaExport.createOnly(EnumSet.of(TargetType.STDOUT), metadata);
	}

}
