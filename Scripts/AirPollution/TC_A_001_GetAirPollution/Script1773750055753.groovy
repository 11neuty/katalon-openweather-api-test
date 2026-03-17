import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import java.nio.file.Files
import java.nio.file.Paths

def response = WS.sendRequest(findTestObject('AirPollution/GetAirPollution'))

WS.verifyResponseStatusCode(response, 200)
assert response.getElapsedTime() < 5000

def json = new JsonSlurper().parseText(response.getResponseBodyContent())

assert json.list != null
assert json.list.size() > 0

def schemaText = new String(Files.readAllBytes(Paths.get("Include/schema/air_schema.json")))
WS.validateJsonAgainstSchema(response, schemaText)