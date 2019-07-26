package com.netposa.ips.ga1400.util;

import com.alibaba.fastjson.JSONArray;
import com.netposa.ips.ga1400.constants.ActionConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Slf4j
public class CommonUtils {
    /**
     * 创建uuid
     *
     * @param removeSplit 是否包含分割符
     * @param toUpper     是否转为大写
     * @return uuid
     */
    public static String createUUID(boolean removeSplit, boolean toUpper) {
        String result = UUID.randomUUID().toString();

        if (removeSplit) {
            result = result.replace("-", "");
        }

        if (toUpper) {
            result = result.toUpperCase();
        }
        return result;
    }

    /**
     * 时间转字符串
     *
     * @param instant
     * @param formatter
     * @return
     */
    public static String getDatetimeString(Instant instant, String formatter) {
        formatter = Strings.isBlank(formatter) ? ActionConstants.LOCAL_DATETIME_FORMATTER : formatter;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter).withZone(ZoneId.systemDefault());
        return dateTimeFormatter.format(instant);
    }

    /**
     * 字符串转时间
     *
     * @param datetimeStr
     * @param formatter
     * @return
     */
    public static Instant getDatetimeByString(String datetimeStr, String formatter) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter).withZone(ZoneId.systemDefault());
        return Instant.from(dateTimeFormatter.parse(datetimeStr));
    }

    /**
     * Integer求和
     *
     * @param list
     * @return
     */
    public static Integer sumIntegerList(List<Integer> list) {
        Integer result = 0;
        for (Integer i : list) {
            result = result + i;
        }
        return result;
    }

    /**
     * 根据时间获取目录
     *
     * @param basePath
     * @param instant
     * @return
     */
    public static String createPath(String basePath, Instant instant) {
        String path = String.format("%s/%s/", basePath, CommonUtils.getDatetimeString(instant, "yyyy/MM/dd/HH"));
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            path = null;
        }
        return path;
    }

    /**
     * http jpg 转 base64
     *
     * @param urlStr
     * @return
     */
    public static String httpPicToBase64(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = conn.getInputStream();
        byte[] buffer = new byte[1024 * 4];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        String base64 = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        inputStream.close();
        byteArrayOutputStream.close();
        return base64;
    }

    /**
     * base64转文件
     *
     * @param base64Str
     * @param filePath
     * @return
     */
    public static boolean base64ToFile(String base64Str, String filePath) {
        boolean result = false;
        // base64 转 bytes
        byte[] datas = Base64.getDecoder().decode(base64Str);
        try {
            Files.write(Paths.get(filePath), datas);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存文件
     *
     * @param path
     * @param fileName
     * @param fileData
     * @return
     */
    public static String saveFile(String path, String fileName, byte[] fileData) {
        String result;
        try {
            Files.createDirectories(Paths.get(path));
            String filePath = String.format("%s/%s", path, fileName);
            Files.write(Paths.get(filePath), fileData);
            result = filePath;
        } catch (IOException e) {
            log.error("保存文件失败 {}/{} {}", path, fileName, e.getMessage());
            result = null;
        }
        return result;
    }

    /**
     * 将entityList转换成modelList
     *
     * @param fromList
     * @param tClass
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> List<T> entityListToModelList(List<F> fromList, Class<T> tClass) {
        if (fromList.isEmpty() || fromList == null) {
            return null;
        }
        List<T> tList = new ArrayList<>();
        for (F f : fromList) {
            T t = entityToModel(f, tClass);
            tList.add(t);
        }
        return tList;
    }

    /**
     * 将entity转换成model
     *
     * @param entity
     * @param modelClass
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> T entityToModel(F entity, Class<T> modelClass) {
        log.debug("entityToModel : Entity属性的值赋值到Model");
        Object model = null;
        if (entity == null || modelClass == null) {
            return null;
        }

        try {
            model = modelClass.newInstance();
        } catch (InstantiationException e) {
            log.error("entityToModel : 实例化异常", e);
        } catch (IllegalAccessException e) {
            log.error("entityToModel : 安全权限异常", e);
        }
        BeanUtils.copyProperties(entity, model);
        return (T) model;
    }

    /**
     * 判断为null,或者长度为0
     *
     * @param o
     * @return
     */
    public static boolean arrayIsNullOrEmpty(Object o) {
        if (o == null) {
            return true;
        } else if (o instanceof List) {
            List list = (List) o;
            if (list.size() < 1) {
                return true;
            }
        } else if (o instanceof JSONArray) {
            JSONArray array = (JSONArray) o;
            if (array.size() < 1) {
                return true;
            }
        } else if (o.getClass().isArray()) {
            Object[] objects = (Object[]) o;
            if (objects.length < 1) {
                return true;
            }
        }

        return false;
    }
}
