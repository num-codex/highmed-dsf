package org.highmed.pseudonymization.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.BitSet;

import org.highmed.openehr.model.datatypes.DoubleRowElement;
import org.highmed.openehr.model.datatypes.StringRowElement;
import org.highmed.openehr.model.datatypes.ZonedDateTimeRowElement;
import org.highmed.pseudonymization.domain.impl.FhirMdatContainer;
import org.highmed.pseudonymization.domain.impl.MatchedPersonImpl;
import org.highmed.pseudonymization.domain.impl.MedicIdImpl;
import org.highmed.pseudonymization.domain.impl.OpenEhrMdatContainer;
import org.highmed.pseudonymization.domain.impl.PersonImpl;
import org.highmed.pseudonymization.domain.impl.PseudonymizedPersonImpl;
import org.highmed.pseudonymization.domain.json.TtpObjectMapperFactory;
import org.highmed.pseudonymization.recordlinkage.MatchedPerson;
import org.highmed.pseudonymization.recordlinkage.MedicId;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.uhn.fhir.context.FhirContext;

public class PseudonymizedPersonImplJsonTest
{
	private static final Logger logger = LoggerFactory.getLogger(PseudonymizedPersonImplJsonTest.class);

	private ObjectMapper objectMapper = TtpObjectMapperFactory.createObjectMapper(FhirContext.forR4());

	@Test
	public void testWriteReadOpenEhr() throws Exception
	{
		MedicId medicId = new MedicIdImpl("org", "value");
		BitSet recordBloomFilter = new BitSet(5000);
		recordBloomFilter.set(200);
		recordBloomFilter.set(4850);
		OpenEhrMdatContainer mdatContainer = new OpenEhrMdatContainer(Arrays.asList(new StringRowElement("string"),
				new DoubleRowElement(0.1), new ZonedDateTimeRowElement(ZonedDateTime.now())));

		PersonWithMdat person = new PersonImpl(medicId, recordBloomFilter, mdatContainer);
		MatchedPerson<PersonWithMdat> matchedPerson = new MatchedPersonImpl(person);
		PseudonymizedPersonWithMdat pseudonymizedPerson = new PseudonymizedPersonImpl(matchedPerson, "pseudonym");

		String value = objectMapper.writeValueAsString(pseudonymizedPerson);
		assertNotNull(value);

		logger.debug("matchedPerson: {}", value);

		PseudonymizedPersonWithMdat readPerson = objectMapper.readValue(value, PseudonymizedPersonImpl.class);
		assertNotNull(readPerson);
		assertNotNull(readPerson.getMdatContainers());
		assertEquals(1, readPerson.getMdatContainers().size());
	}

	@Test
	public void testWriteReadFhir() throws Exception
	{
		MedicId medicId = new MedicIdImpl("org", "value");
		BitSet recordBloomFilter = new BitSet(5000);
		recordBloomFilter.set(200);
		recordBloomFilter.set(4850);
		FhirMdatContainer mdatContainer = new FhirMdatContainer(
				Arrays.asList(new Bundle().setType(BundleType.SEARCHSET), new Bundle().setType(BundleType.SEARCHSET)));

		PersonWithMdat person = new PersonImpl(medicId, recordBloomFilter, mdatContainer);
		MatchedPerson<PersonWithMdat> matchedPerson = new MatchedPersonImpl(person);
		PseudonymizedPersonWithMdat pseudonymizedPerson = new PseudonymizedPersonImpl(matchedPerson, "pseudonym");

		String value = objectMapper.writeValueAsString(pseudonymizedPerson);
		assertNotNull(value);

		logger.debug("matchedPerson: {}", value);

		PseudonymizedPersonWithMdat readPerson = objectMapper.readValue(value, PseudonymizedPersonImpl.class);
		assertNotNull(readPerson);
		assertNotNull(readPerson.getMdatContainers());
		assertEquals(1, readPerson.getMdatContainers().size());
	}
}
