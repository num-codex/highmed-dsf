package org.highmed.fhir.dao;

import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;

public class DaoProviderImpl implements DaoProvider, InitializingBean
{
	private final CodeSystemDao codeSystemDao;
	private final EndpointDao endpointDao;
	private final HealthcareServiceDao healthcareServiceDao;
	private final LocationDao locationDao;
	private final OrganizationDao organizationDao;
	private final PatientDao patientDao;
	private final PractitionerDao practitionerDao;
	private final ProvenanceDao provenanceDao;
	private final ResearchStudyDao researchStudyDao;
	private final StructureDefinitionDao structureDefinitionDao;
	private final StructureDefinitionSnapshotDao structureDefinitionSnapshotDao;
	private final SubscriptionDao subscriptionDao;
	private final TaskDao taskDao;
	private final ValueSetDao valueSetDao;

	public DaoProviderImpl(CodeSystemDao codeSystemDao, EndpointDao endpointDao,
			HealthcareServiceDao healthcareServiceDao, LocationDao locationDao, OrganizationDao organizationDao,
			PatientDao patientDao, PractitionerDao practitionerDao, ProvenanceDao provenanceDao,
			ResearchStudyDao researchStudyDao, StructureDefinitionDao structureDefinitionDao,
			StructureDefinitionSnapshotDao structureDefinitionSnapshotDao, SubscriptionDao subscriptionDao,
			TaskDao taskDao, ValueSetDao valueSetDao)
	{
		this.codeSystemDao = codeSystemDao;
		this.endpointDao = endpointDao;
		this.healthcareServiceDao = healthcareServiceDao;
		this.locationDao = locationDao;
		this.organizationDao = organizationDao;
		this.patientDao = patientDao;
		this.practitionerDao = practitionerDao;
		this.provenanceDao = provenanceDao;
		this.researchStudyDao = researchStudyDao;
		this.structureDefinitionDao = structureDefinitionDao;
		this.structureDefinitionSnapshotDao = structureDefinitionSnapshotDao;
		this.subscriptionDao = subscriptionDao;
		this.taskDao = taskDao;
		this.valueSetDao = valueSetDao;
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		Objects.requireNonNull(codeSystemDao, "codeSystemDao");
		Objects.requireNonNull(endpointDao, "endpointDao");
		Objects.requireNonNull(healthcareServiceDao, "healthcareServiceDao");
		Objects.requireNonNull(locationDao, "locationDao");
		Objects.requireNonNull(organizationDao, "organizationDao");
		Objects.requireNonNull(patientDao, "patientDao");
		Objects.requireNonNull(practitionerDao, "practitionerDao");
		Objects.requireNonNull(provenanceDao, "provenanceDao");
		Objects.requireNonNull(researchStudyDao, "researchStudyDao");
		Objects.requireNonNull(structureDefinitionDao, "structureDefinitionDao");
		Objects.requireNonNull(structureDefinitionSnapshotDao, "structureDefinitionSnapshotDao");
		Objects.requireNonNull(subscriptionDao, "subscriptionDao");
		Objects.requireNonNull(taskDao, "taskDao");
		Objects.requireNonNull(valueSetDao, "valueSetDao");
	}

	@Override
	public CodeSystemDao getCodeSystemDao()
	{
		return codeSystemDao;
	}

	@Override
	public EndpointDao getEndpointDao()
	{
		return endpointDao;
	}

	@Override
	public HealthcareServiceDao getHealthcareServiceDao()
	{
		return healthcareServiceDao;
	}

	@Override
	public LocationDao getLocationDao()
	{
		return locationDao;
	}

	@Override
	public OrganizationDao getOrganizationDao()
	{
		return organizationDao;
	}

	@Override
	public PatientDao getPatientDao()
	{
		return patientDao;
	}

	@Override
	public PractitionerDao getPractitionerDao()
	{
		return practitionerDao;
	}

	@Override
	public ProvenanceDao getProvenanceDao()
	{
		return provenanceDao;
	}

	@Override
	public ResearchStudyDao getResearchStudyDao()
	{
		return researchStudyDao;
	}

	@Override
	public StructureDefinitionDao getStructureDefinitionDao()
	{
		return structureDefinitionDao;
	}

	@Override
	public StructureDefinitionSnapshotDao getStructureDefinitionSnapshotDao()
	{
		return structureDefinitionSnapshotDao;
	}

	@Override
	public SubscriptionDao getSubscriptionDao()
	{
		return subscriptionDao;
	}

	@Override
	public TaskDao getTaskDao()
	{
		return taskDao;
	}

	@Override
	public ValueSetDao getValueSetDao()
	{
		return valueSetDao;
	}
}
