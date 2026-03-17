import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

def response = WS.sendRequest(findTestObject('AirPollution/GetAirPollution'))

WS.verifyResponseStatusCode(response, 200)
assert response.getElapsedTime() < 5000

def json = new JsonSlurper().parseText(response.getResponseBodyContent())

assert json.list != null
assert json.list.size() > 0

def comp = json.list[0].components

assert comp != null
assert comp.co >= 0
assert comp.pm2_5 >= 0
assert comp.pm10 >= 0