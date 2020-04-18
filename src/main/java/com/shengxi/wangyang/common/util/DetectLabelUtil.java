package com.shengxi.wangyang.common.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tiia.v20190529.TiiaClient;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelItem;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelRequest;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelResponse;
import java.util.Collection;
import java.util.HashMap;


/**
 * @author y
 * @date 2020-03-10 14:32:22
 */
public class DetectLabelUtil {

    //"{\"ImageBase64\":\"}
    public static Collection<String> detectLabel(String params) {
        if (params == null) {
            params = "{}";
        }
        try {
            Credential cred = new Credential("AKIDiJgneGs24sQlrg4xKSM1tWt1zjEjR43m", "ctvjHgy1pMdfV6S8ankWVGl1C8pzua2G");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("tiia.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            TiiaClient client = new TiiaClient(cred, "ap-guangzhou", clientProfile);
            DetectLabelRequest req = DetectLabelRequest.fromJsonString(params, DetectLabelRequest.class);
            DetectLabelResponse resp = client.DetectLabel(req);
            DetectLabelItem[] labels = resp.getLabels();
            HashMap<String, String> result = tomap(labels);
            return toColl(result);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Collection<String> toColl(HashMap<String, String> result) {
        return result.values();
    }

    private static HashMap<String, String> tomap(DetectLabelItem[] labels) {
        HashMap<String, String> resultMap = new HashMap<>();
        for (DetectLabelItem label : labels) {
            label.toMap(resultMap, "");
        }
        return resultMap;
    }

}
