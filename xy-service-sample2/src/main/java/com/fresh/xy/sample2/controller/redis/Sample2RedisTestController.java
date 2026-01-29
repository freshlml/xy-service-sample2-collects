package com.fresh.xy.sample2.controller.redis;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fresh.common.result.JsonResult;
import com.fresh.xy.redis.config.FlCustomSerializer;
import com.fresh.xy.redis.dto.ForTestPojo2RedisDto;
import com.fresh.xy.redis.enums.ForRedisTestPojoDtoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sample2RedisTest/")
public class Sample2RedisTestController {

    //@Autowired
    private RedisTemplate<String, byte[]> genericRedisTemplate;

    //@Autowired
    private FlCustomSerializer flCustomSerializer;

    @PostMapping("setBytes")
    public JsonResult setBytes() {

        byte[] nullBytes = genericRedisTemplate.opsForValue().get("Null");//从redis取出，保存为 byte[]
        Object nullVal1 = flCustomSerializer.deserialize(nullBytes); //反序列化为 null
        Integer nullVal2 = flCustomSerializer.deserialize(nullBytes, Integer.class);//反序列化为 null


        byte[] booleanBytes = genericRedisTemplate.opsForValue().get("Boolean");//从redis取出，保存为 byte[]
        Boolean booleanVal = flCustomSerializer.deserialize(booleanBytes, Boolean.class);//反序列化为 Boolean:true


        byte[] charBytes = genericRedisTemplate.opsForValue().get("Character");//从redis取出，保存为 byte[]
        Character charVal = flCustomSerializer.deserialize(charBytes, Character.class);//反序列化为 Character:'a'


        byte[] byteBytes = genericRedisTemplate.opsForValue().get("Byte");//从redis取出，保存为 byte[]
        Byte byteVal = flCustomSerializer.deserialize(byteBytes, Byte.class);//反序列化为 Byte:1


        byte[] shortBytes = genericRedisTemplate.opsForValue().get("Short");  //从redis取出，保存为 byte[]
        Short shortVal = flCustomSerializer.deserialize(shortBytes, Short.class);//反序列化为 Short:2


        byte[] integerBytes = genericRedisTemplate.opsForValue().get("Integer");//从redis取出，保存为 byte[]
        Integer integerVal = flCustomSerializer.deserialize(integerBytes, Integer.class);//反序列化为 Integer:3


        byte[] longBytes = genericRedisTemplate.opsForValue().get("Long");//从redis取出，保存为byte[]
        Long longVal = flCustomSerializer.deserialize(longBytes, Long.class);//反序列化为 Long:1234567890987666L


        byte[] stringBytes = genericRedisTemplate.opsForValue().get("String");//从redis取出，保存为 byte[]
        String stringVal = flCustomSerializer.deserialize(stringBytes, String.class);//反序列化为 String:"123中"


        byte[] floatBytes = genericRedisTemplate.opsForValue().get("Float");//从redis取出，保存为 byte[]
        Float floatVal = flCustomSerializer.deserialize(floatBytes, Float.class);//反序列化为 Float


        byte[] doubleBytes = genericRedisTemplate.opsForValue().get("Double");//从redis取出，保存为 byte[]
        Double doubleVal = flCustomSerializer.deserialize(doubleBytes, Double.class);//反序列化为 Double

        BigInteger bi = new BigInteger("771123123123123123123213123213333333333333333333333333333333333313123123123123123213123123123123123123123121");
        BigDecimal bd = new BigDecimal("8.9999011231312312312323123123123123123123123123123123123123123123123123123234434541353453645364356421432423");


        byte[] bigIntegerBytes = genericRedisTemplate.opsForValue().get("BigInteger");//从redis取出，保存为 byte[]
        BigInteger biVal = flCustomSerializer.deserialize(bigIntegerBytes, BigInteger.class);//反序列化为BigInteger


        byte[] bigDecimalBytes = genericRedisTemplate.opsForValue().get("BigDecimal");//从redis取出，保存为 byte[]
        BigDecimal bdVal = flCustomSerializer.deserialize(bigDecimalBytes, BigDecimal.class);//反序列化为BigDecimal


        byte[] enumBytes = genericRedisTemplate.opsForValue().get("Enum");//从redis取出，保存为 byte[]
        ForRedisTestPojoDtoEnum enumVal = ForRedisTestPojoDtoEnum.getByValue(flCustomSerializer.deserialize(enumBytes, String.class));//反序列化并使用该String构造Enum


        byte[] localDateTimeBytes = genericRedisTemplate.opsForValue().get("LocalDateTime");//从redis取出，保存为 byte[]
        LocalDateTime localDateTimeVal = flCustomSerializer.deserialize(localDateTimeBytes, LocalDateTime.class);//反序列为LocalDateTime


        byte[] listBytes = genericRedisTemplate.opsForValue().get("List");//从redis取出, 保存为 byte[]
        List<String> listVal = flCustomSerializer.deserialize(listBytes, new TypeReference<List<String>>(){}); //反序列化为List<String>


        Long length = genericRedisTemplate.opsForList().size("list-value");
        //从redis取出，保存为List<String>: "\"2021-05-16 14:03:19\"", "\"SYSTEM\"", "[\"java.math.BigDecimal\",8.9999011231312312312323123123123123123123123123123123123123123123123123123234434541353453645364356421432423]", "\"中\"", "4123123122"
        List<byte[]> listValues = genericRedisTemplate.opsForList().range("list-value", 0, length);
        //对每一个值反序列化...


        byte[] hashKey1 = (byte[]) genericRedisTemplate.opsForHash().get("hash-value", "key1");//从redis取出，保存为 byte[]
        String hashKey1Val = flCustomSerializer.deserialize(hashKey1, String.class);//反序列化为String: "SYSTEM"

        byte[] hashKey2 = (byte[]) genericRedisTemplate.opsForHash().get("hash-value", "key2");//从redis取出，保存为 byte[]
        LocalDateTime hashKey2Val = flCustomSerializer.deserialize(hashKey2, LocalDateTime.class);//反序列化为LocalDateTime

        byte[] hashKey3 = (byte[]) genericRedisTemplate.opsForHash().get("hash-value", "key3");//从redis取出，保存为 byte[]
        BigDecimal hashKey3Val = flCustomSerializer.deserialize(hashKey3, BigDecimal.class);//反序列化为BigDecimal

        byte[] hashKey4 = (byte[]) genericRedisTemplate.opsForHash().get("hash-value", "key4");//从redis取出，保存为 byte[]
        String hashKey4Val = flCustomSerializer.deserialize(hashKey4, String.class);//反序列化为 String: "中"

        byte[] hashKey5 = (byte[]) genericRedisTemplate.opsForHash().get("hash-value", "key5");//从redis取出，保存为 byte[]
        Long hashKey5Val = flCustomSerializer.deserialize(hashKey5, Long.class);//反序列化为 Long


        ForTestPojo2RedisDto pojo2RedisDto = ForTestPojo2RedisDto.builder()
                .id(1234534535354L)
                .bl(false)
                .s(null)
                .name("just pojo哒哒哒")
                .bi(bi)
                .bd(bd)
                .pojoType(ForRedisTestPojoDtoEnum.SYSTEM.getValue())
                .pojoTime(LocalDateTime.now())
                .build();
        //1.序列化Pojo为 byte[]
        byte[] se = flCustomSerializer.serializeAsBytes(pojo2RedisDto);
        //2.保存到redis
        //"[\"com.sc.sample.redis.dto.Pojo2RedisDto\",{\"id\":1234534535354,\"bl\":false,\"s\":null,\"name\":\"just pojo\xe5\x93\x92\xe5\x93\x92\xe5\x93\x92\",\"bi\":[\"java.math.BigInteger\",771123123123123123123213123213333333333333333333333333333333333313123123123123123213123123123123123123123121],\"bd\":[\"java.math.BigDecimal\",8.9999011231312312312323123123123123123123123123123123123123123123123123123234434541353453645364356421432423],\"pojoType\":\"SYSTEM\",\"pojoTime\":\"2021-05-17 22:30:39\"}]"
        genericRedisTemplate.opsForValue().set("Pojo", se);
        //3.从redis取出，保存为byte[]
        byte[] result = genericRedisTemplate.opsForValue().get("Pojo");
        //4.反序列化为Pojo
        ForTestPojo2RedisDto pojoDe = flCustomSerializer.deserialize(result, ForTestPojo2RedisDto.class);


        return JsonResult.buildSuccessResult(pojoDe);
    }


}
