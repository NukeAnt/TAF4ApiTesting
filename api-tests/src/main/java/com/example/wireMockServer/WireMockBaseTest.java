package com.example.wireMockServer;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WireMockBaseTest
{
  private static String PROTOCOL = "http";
  private static String HOST = "localhost";

  private WireMockServer wireMockServer;

  public String getBaseUrl()
  {
    return String.format("%s://%s:%d", PROTOCOL, HOST, wireMockServer.port());
  }

  public void startWireMockServer()
  {
    stopWireMockServer();
    wireMockServer =
        new WireMockServer(WireMockConfiguration.options()
            .dynamicHttpsPort()
            .dynamicPort()
            .notifier(new ConsoleNotifier(true)));
    wireMockServer.start();
    log.info("WireMock server started at: " + getBaseUrl());

    WireMock.configureFor(HOST, wireMockServer.port());

    wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/getInitialResponse"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBodyFile("GetInitialDataSetResponse.json") // files in __files directory)
        ));
  }

  public void stopWireMockServer()
  {
    if (wireMockServer != null && wireMockServer.isRunning())
    {
      wireMockServer.stop();
      log.info("WireMock server stopped.");
    }
  }
}
