//package com.netposa.dgep.remoteApi;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.*;
//
//@FeignClient(
//        url = "${remoteservice.backend-api}",
//        name = "backend-api"
//)
//@RequestMapping("face-collector")
//public interface BackendApi {
//
//    /**
//     * 导入人脸图片
//     */
//    @PostMapping("face")
//    String saveFace(@RequestBody JSONObject jsonObject);
//
//    /**
//     * 删除人脸
//     * @param faceIds
//     * @return
//     */
//    @DeleteMapping("face")
//    String removeFace(@RequestParam("faceids") String faceIds);
//
//    /**
//     * 创建人像库
//     */
//    @PostMapping("repository")
//    String saveRepository(@RequestBody JSONObject jsonObject);
//
//    /**
//     * 删除人像库
//     * @param id
//     * @return
//     */
//    @DeleteMapping("repository")
//    String removeRepository(@RequestParam("id") String id);
//
//    /**
//     * 创建布控任务
//     */
//    @PostMapping("task")
//    String saveTask(@RequestBody JSONObject jsonObject);
//
//    /**
//     * 删除布控任务
//     * @param taskid
//     * @return
//     */
//    @DeleteMapping("task")
//    String removeTask(@RequestParam("taskid") String taskid);
//
//}
