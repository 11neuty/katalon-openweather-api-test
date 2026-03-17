import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

def response = WS.sendRequest(findTestObject('Weather/Get5DayForecast'))

WS.verifyResponseStatusCode(response, 200)
assert response.getElapsedTime() < 5000

def json = new JsonSlurper().parseText(response.getResponseBodyContent())

assert json.list != null
assert json.list.size() > 0

def data = json.list[0]

assert data.dt != null
assert data.main != null
assert data.main.temp != null
assert data.main.humidity != null
assert data.weather != null
assert data.weather.size() > 0
assert data.weather[0].description != null