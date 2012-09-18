/**
 * 
 */
package uk.co.vurt.hakken.client.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.vurt.hakken.domain.job.DataItem;
import uk.co.vurt.hakken.domain.job.JobDefinition;
import uk.co.vurt.hakken.domain.task.TaskDefinition;

/**
 * @author giles
 * 
 */
public class JacksonStreamParserTest {

	private BufferedInputStream in = null;

	@Before
	public void setup() throws IOException {
		
	}

	@After
	public void teardown() throws IOException {
		if (in != null) {
			in.close();
		}

		in = null;
	}

	/**
	 * Test method for
	 * {@link uk.co.vurt.hakken.client.json.JacksonStreamParser#parseJobDefinitionStream(java.io.InputStream)}
	 * .
	 */
	@Test
	public void testParseJobDefinitionStream() {
		in = new BufferedInputStream(getClass().getResourceAsStream(
				"/json_stream_parser_1.js"));
		
		JacksonStreamParser streamParser = new JacksonStreamParser();
		try {
			List<JobDefinition> jobs = streamParser.parseJobDefinitionStream(in);
			assertEquals(1, jobs.size());
			JobDefinition testJob = jobs.get(0);
			assertNotNull(testJob);
			assertEquals(1l, testJob.getId().longValue());
			assertEquals("Test Job 1", testJob.getName());
			assertEquals("AWAITING", testJob.getStatus());
			assertEquals("This is a test job", testJob.getNotes());
			Set<DataItem> dataItems = testJob.getDataItems();
			assertNotNull(dataItems);
			assertEquals(1l,dataItems.size());
			Iterator<DataItem> iterator = dataItems.iterator();
			while(iterator.hasNext()){
				DataItem dataItem = iterator.next();
				assertEquals("TEST_ITEM_1", dataItem.getName());
				assertNull(dataItem.getType());
				assertEquals("1", dataItem.getValue());
				assertEquals("PAGE_1", dataItem.getPageName());
			}
			assertFalse(testJob.isModified());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testParseJobDefinitionStream2() {
		in = new BufferedInputStream(getClass().getResourceAsStream(
				"/single_job_full_task_definition.js"));
		
		JacksonStreamParser streamParser = new JacksonStreamParser();
		try {
			List<JobDefinition> jobs = streamParser.parseJobDefinitionStream(in);
			assertEquals(1, jobs.size());
			JobDefinition testJob = jobs.get(0);
			assertNotNull(testJob);
			assertEquals(61057l, testJob.getId().longValue());
			assertEquals("HSC/22231 - Status: Appointment", testJob.getName());
			assertEquals("AWAITING", testJob.getStatus());
			assertEquals("123 ANY STREET, SOMETOWN, A12 3BC <b>(validated)</b>", testJob.getNotes());
			Set<DataItem> dataItems = testJob.getDataItems();
			assertNotNull(dataItems);
			assertEquals(41,dataItems.size());
			assertFalse(testJob.isModified());
			
			TaskDefinition definition = testJob.getDefinition();
			assertNotNull(definition);
			assertEquals(0l, definition.getId());
			assertEquals("hfrc_assessment", definition.getName());
			assertEquals("HFRC Workbook", definition.getDescription());
			assertEquals(23, definition.getPages().size());
			
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
