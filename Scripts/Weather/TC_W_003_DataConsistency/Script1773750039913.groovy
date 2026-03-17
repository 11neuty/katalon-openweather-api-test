import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

def response = WS.sendRequest(findTestObject('Weather/Get5DayForecast'))

WS.verifyResponseStatusCode(response, 200)
assert response.getElapsedTime() < 5000

def json = new JsonSlurper().parseText(response.getResponseBodyContent())

assert json.list != null
assert json.list.size() > 0

json.list.each {
    assert it.main.temp != null
    assert it.main.humidity >= 0 && it.main.humidity <= 100
    assert it.weather != null
    assert it.weather.size() > 0
}