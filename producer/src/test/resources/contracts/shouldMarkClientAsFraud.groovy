package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'PUT'
        url '/fraudcheck'
        body("""
    {
      "clientId": "1234567890",
      "loanAmount": 99999
    }
    """)
        headers {
            header('Content-Type', 'application/vnd.fraud.v1+json')
        }
    }

    response {
        status 200
        body("""
  {
    "fraudCheckStatus": "FRAUD",
    "rejectionReason": "Amount too high"
  }
  """)
        headers {
            header('Content-Type': 'application/vnd.fraud.v1+json;charset=UTF-8')
        }
    }
}

