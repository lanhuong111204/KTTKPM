class IJsonTarget {
  processData(jsonData) {
    throw new Error("method processData(jsonData) must be implemented");
  }
}

class LegacyXmlSystem {
  sendXmlRequest(xmlData) {
    console.log(`\n[LegacySystem] Đã nhận dự liệu xml: ${xmlData}`);
    console.log(`[LegacySystem] Đang xử lý ....`);
    const responseXml = `<response> 
            <status>Success</status>
            <message>Giao dịch thành công</message>
        </response>`;
    console.log(`[LegacySystem] Trả về kết quả xml: ${responseXml}`);

    return responseXml;
  }
}

class XmlToJsonAdapter extends IJsonTarget {
  #legacySystem;
  constructor(legacySystem) {
    super();
    this.#legacySystem = legacySystem;
  }

  #convertJsonToXml(jsonString) {
    console.log(`[Adapter] Đang chuyển: Json --> XML`);

    const obj = JSON.parse(jsonString);

    return `<request>
            <userId>${obj.userId}</userId>
            <action>${obj.action}</action>
        </request>`;
  }
  #convertXmlToJson(xmlString) {
    console.log(`[Adapter] Đang chuyển: XML --> JSON...`);
    if (xmlString.includes("<status>Success</status>")) {
      return JSON.stringify({
        status: "Success",
        message: "Giao dịch thành công",
      });
    }
    return JSON.stringify({
      status: "error",
      message: "Lỗi giao dịch",
    });
  }

  processData(jsonData) {
    console.log(`[Adapter] nhận JSON từ client`);
    const xmlRequest = this.#convertJsonToXml(jsonData);

    const xmlResponse = this.#legacySystem.sendXmlRequest(xmlRequest);

    const jsonResponse = this.#convertXmlToJson(xmlResponse);

    return jsonResponse;
  }
}

class ModernAppClient {
  #apiService;
  constructor(apiService) {
    this.#apiService = apiService;
  }

  sendDataToServer() {
    const payload = JSON.stringify({
      userId: "1234",
      action: "Transfer_Money",
    });
    console.log(`[Client] Gửi Payload (JSON)`, payload);

    const responseData = this.#apiService.processData(payload);

    console.log(`\n[Client] Nhận được kết quả (JSON):`, responseData);
  }
}

console.log("===Test Adapter Pattern===");

const coreBankingSystem = new LegacyXmlSystem();

const apiAdapter = new XmlToJsonAdapter(coreBankingSystem);

const myApp = new ModernAppClient(apiAdapter);

myApp.sendDataToServer();
