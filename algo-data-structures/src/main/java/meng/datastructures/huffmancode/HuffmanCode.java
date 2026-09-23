package meng.datastructures.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码
 */
public class HuffmanCode {
    /**
     *完成对压缩文件的解压
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            Map<String,Object> ans = (Map<String,Object>)ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, ans);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println(e2.getMessage());
            }

        }
    }
    /**
     *完成对压缩数据的解码
     * @param huffmanCodes 赫夫曼编码表 map
     * @param ans 赫夫曼编码得到的map
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, Map<String, Object> ans) {
        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        String val =(String) ans.get("ends");
        byte[] bytes = (byte[]) ans.get("bytes");
        for(int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            stringBuilder.append(byteToBitString(b));
        }
        stringBuilder.append(val);
        Map<String, Byte>  map = new HashMap<>();

        for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for(int  i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;
            while(flag) {
                //1010100010111...
                //递增的取出 key 1
                String key ="";
                if (i+count>stringBuilder.length()){
                    i=i+count;
                    flag =false;
                }
                else
                    key = stringBuilder.substring(i, i+count);//i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if(b == null) {//说明没有匹配到
                    count++;
                }else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到 count
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }
    /**
     * 将一个byte 转成一个二进制的字符串
     * @param b 传入的 byte
     * @return 是该b 对应的二进制的字符串，（注意是按补码返回）
     */
    private static String byteToBitString(byte b) {
        //使用变量保存 b
        int temp = b; //将 b 转成 int
        temp |= 256;
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        while (str.length() < 8)
            str = "0"+str;
        return str.substring(str.length()-8);
    }
    /**
     *将一个文件进行压缩
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            Map<String,Object> ans = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(ans); //我们是把
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(mapCode);
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            }catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }
        }

    }
    /**
     * @param bytes 待处理的数据
     * @return 是经过赫夫曼编码处理后的字节数组(压缩后的数组
     */
    private static Map<String, Object> huffmanZip(byte[] bytes) {
        //构建节点
        List<Node> nodes = getNodes(bytes);
        //根据 nodes 创建的赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的赫夫曼编码(根据 赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        Map<String, Object> zip = zip(bytes, huffmanCodes);
        return zip;
    }
    /**
     * 构建所需的节点
     * @param bytes
     * @return
     */
    private static List<Node> getNodes(byte[] bytes) {
        Map<Byte,Integer> map = new HashMap<>();
        for (byte b : bytes)
            map.put(b,map.getOrDefault(b,0)+1);
        List<Node> list = new ArrayList<>();
        for(byte b : map.keySet()){
            Node node = new Node(b,map.get(b));
            list.add(node);
        }
        return list;
    }
    /**
     * 构建所需的二叉树
     * @param nodes
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size()>1){
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null,left.weight+right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    /**
     * 创建对应的编码表
     * @param huffmanTreeRoot
     * @return
     */
    static Map<Byte,String> mapCode = new HashMap<>();
    static StringBuffer sbCode = new StringBuffer();
    private static Map<Byte, String> getCodes(Node huffmanTreeRoot) {
        if (huffmanTreeRoot == null)
            return null;
        getCodes(huffmanTreeRoot.left,"0",sbCode);
        getCodes(huffmanTreeRoot.right,"1",sbCode);
        return mapCode;
    }
    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param node 节点
     * @param code  路径值
     * @param sb    连接串
     */
    private static void getCodes(Node node, String code, StringBuffer sb) {
        //创建新的字符连接串，避免后面的结果影响前面的结果
        StringBuffer temp = new StringBuffer(sb);
        temp.append(code);
        if (node != null){
            //当前为合并节点,不需要记录
            if (node.data == null){
                getCodes(node.left,"0",temp);
                getCodes(node.right,"1",temp);
            }else {
                //当前为叶子结点
                mapCode.put(node.data,temp.toString());
            }
        }
    }
    /**
     * 将原有的二维数据按照编码表进行重新对应
     * 返回能过对应的8位的字节数据
     * 和
     * 未能凑够八位的末尾数据
     * @param bytes
     * @param maps
     * @return
     */
    private static Map<String,Object> zip(byte[] bytes, Map<Byte, String> maps) {
        Map<String ,Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes)
            sb.append(maps.get(b));
        String val = "";
        int len = 0;
        if (sb.length()%8==0){
            len = sb.length()/8;
        }else {
            len = sb.length()/8;
            val = sb.substring(sb.length()-sb.length()%8);
        }
        byte[] keys = new byte[len];
        for(int i = 0 ; i < len ; i++){
            String str = sb.substring(i*8,i*8+8);
            keys[i] =(byte)Integer.parseInt(str,2);
        }
        map.put("bytes",keys);
        map.put("ends",val);
        return map;
    }
    public static void main(String[] args) {
       String srcFile = "C:\\Users\\lenovo\\Desktop\\1.PNG";
       String destFile = "C:\\Users\\lenovo\\Desktop\\1.zip";
       zipFile(srcFile,destFile);
       String srcFile1 = "C:\\Users\\lenovo\\Desktop\\10.PNG";
       unZipFile(destFile,srcFile1);
    }
}
