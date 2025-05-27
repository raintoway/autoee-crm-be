package cn.iocoder.yudao.server;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlTableNameConverter {

    public static void main(String[] args) {
        try {
            String inputFile = "aaa.sql";
            String outputFile = "aaa_uppercase.sql";

            String content = new String(
                Files.readAllBytes(Paths.get(inputFile)),
                StandardCharsets.UTF_8
            );

            Pattern pattern = Pattern.compile("`([^`]+)`");
            Matcher matcher = pattern.matcher(content);
            StringBuffer sb = new StringBuffer();

            while (matcher.find()) {
                String tableName = matcher.group(1);
                String replacement;
                // 将表名转为小写以检查前缀（不区分大小写）
                String lowerTableName = tableName.toLowerCase();
                if (lowerTableName.startsWith("act_") || lowerTableName.startsWith("flw_")
                || lowerTableName.startsWith("qrtz_")) {
                    replacement = "`" + tableName.toUpperCase() + "`";
                } else {
                    // 保持原样
                    replacement = matcher.group(0);
                }
                matcher.appendReplacement(sb, replacement);
            }
            matcher.appendTail(sb);

            Files.write(
                Paths.get(outputFile),
                sb.toString().getBytes(StandardCharsets.UTF_8)
            );

            System.out.println("处理完成，结果已保存至: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
