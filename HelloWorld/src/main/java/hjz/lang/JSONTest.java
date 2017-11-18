package hjz.lang;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;

public class JSONTest {
	
	
	public static void main(String[] args) throws JSONException {
	
		//String strJSONArray = "[{\"jobCategory\":\"SEGMENT_EXPORT\",\"jobId\":6335361,\"scheduledJobId\":null,\"status\":\"WAITING_TO_RECIEVE_DATA\",\"jobCategory\":\"SEGMENT_EXPORT\",\"jobName\":\"EXPORT_SEGMENT\",\"accountId\":18981,\"subStatus\":\"WAITING_TO_RECIEVE_DATA\",\"errorDetails\":null,\"segmentDataFiles\":null,\"segmentDataFormat\":null,\"destinationSegmentID\":null,\"destinationSegmentName\":\"matty T\",\"destinationSegmentDescription\":null,\"destinationEndpointID\":208751,\"exportCategory\":\"\",\"producerEndpointID\":209031,\"producerSegmentID\":8000000000000001152,\"attributeMappings\":{},\"identityMappings\":null,\"shareAction\":\"ADD\",\"sourceIdentifiers\":[\"email\"],\"destinationIdentifier\":\"Email\"}]";	
		

		String strJSONArray = "[{\"jobCategory\":\"SEGMENT_EXPORT\",\"jobId\":6335361,\"scheduledJobId\":null,\"status\":\"WAITING_TO_RECIEVE_DATA\",\"jobCategory\":\"SEGMENT_EXPORT\",\"jobName\":\"EXPORT_SEGMENT\",\"accountId\":18981,\"subStatus\":\"WAITING_TO_RECIEVE_DATA\",\"errorDetails\":null,\"segmentDataFiles\":null,\"segmentDataFormat\":null,\"destinationSegmentID\":null,\"destinationSegmentName\":\"matty T\",\"destinationSegmentDescription\":null,\"destinationEndpointID\":208751,\"exportCategory\":\"\",\"producerEndpointID\":209031,\"producerSegmentID\":8000000000000001152,\"attributeMappings\":{},\"identityMappings\":null,\"shareAction\":\"ADD\",\"sourceIdentifiers\":null,\"destinationIdentifier\":\"Email\"}]";	
		
		
		JSONArray jobs = new JSONArray(strJSONArray);	
			
		JSONObject job = jobs.getJSONObject(0);
		
		
		// Default to use EMAIL identity
		String identity = "EMail";
		
		//old UBX API response contains identityMapping for backward compatibility
		JSONObject identityMappings = job.optJSONObject("identityMappings");
		//JSONObject identityMappings = job.getJSONObject("identityMappings");
		if (identityMappings!=null && identityMappings.keys().hasNext()) {
			identity = identityMappings.keys().next().toString();
			
		} else {
			
			//new UBX API response contains sourceIdentifiers and destinationIdentifier instead
			JSONArray sourceIdentifiers = job.optJSONArray("sourceIdentifiers");
			if(sourceIdentifiers != null && sourceIdentifiers.length() !=0) {
				identity = sourceIdentifiers.getString(0);
			}
		}

		
		System.out.println(identity);
	
	}
	

}
