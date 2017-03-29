# Simple lending API
Simple lending RESTful API using Spring Boot. Project is fully embedded and easy to launch.

### Running API locally
```
	git clone https://github.com/hubtemp/lending-api.git
	cd lending-api
	./gradlew bootRun
```
You can then access API here: http://localhost:8080/

### Available API requests

| Action | Method | URL | Parameters | 
| ------------- |:-------------:|:-------------:| :-----|
| Get all client loans | GET | /loans |  |
| Get specific client loan by ID | GET | /loans/{loanId} |  |
| Create new loan | POST | /loans | {amount, days} |
| Get specific client loan extensions | GET | /loans/{loan_id}/extensions  |  |
| Create new loan term extension for specific client loan | POST | /loans/{loan_id}/extensions | {days} |