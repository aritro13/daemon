package com.trading.daemon.service;

import io.swagger.client.api.HistoryApi;
import org.springframework.stereotype.Service;
import com.upstox.*;
import com.upstox.auth.*;
import com.upstox.api.*;
import io.swagger.client.api.ChargeApi;

@Service
public class HistoryService {

    public String getInstrumentHistory(String accessToken) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        String res = "";
        // Configure OAuth2 access token for authorization: OAUTH2
        OAuth OAUTH2 = (OAuth) defaultClient.getAuthentication("OAUTH2");
        OAUTH2.setAccessToken(accessToken);

        ChargeApi apiInstance = new ChargeApi();
        HistoryApi historyapiInstance = new HistoryApi();
        String instrumentToken = "NSE_EQ|INE776C01039"; // String | Key of the instrument
        String interval = "1minute"; // String | Product with which the order is to be placed
        String toDate = "2023-03-03"; // String | Indicates whether its a BUY or SELL order
        String from_date = "2023-03-02"; // Float | Price with which the order is to be placed
        String apiVersion = "2.0"; // String | API Version Header
        try {
            GetHistoricalCandleResponse result = historyapiInstance.getHistoricalCandleData1(instrumentToken, interval, toDate, from_date,apiVersion);
            System.out.println(result);
            res = result.toString();
        } catch (ApiException e) {
            System.err.println("Exception when calling ChargeApi#getBrokerage");
            e.printStackTrace();
            res = e.toString();
        }
        return res;
    }
}
