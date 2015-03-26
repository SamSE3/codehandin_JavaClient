///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package moodleclient;
//
///**
// *
// * @author SuperNova
// */
//public class OldFunctions {
//    ////////////////////////////////
//    // Json Printing untils ... more for debugging
//    ////////////////////////////////    
//    /**
//     * Prints out the contents of a JsonObject in a more readable form
//     *
//     * @param jo the JsonObject to be printed
//     */
//    public static void prittyPrintJson(JsonObject jo) {
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(jo));
//    }
//
//    /**
//     * Prints out the contents of a JsonObject in a more readable form,
//     * preceeded by an additional user defined message
//     *
//     * @param jo the JsonObject to be printed
//     * @param message the user defined message to be added before the JsonObject
//     * is printed
//     */
//    public static void prittyPrintJson(JsonObject jo, String message) {
//        System.out.println(message + " " + new GsonBuilder().setPrettyPrinting().create().toJson(jo));
//    }
    ///////////////////////////////////////////////////////////////
    //// this server is not restfull!!!!!!!!!!!!!!
    //// moodle requires a server 
    ///////////////////////////////////////////////////////////////
//    public interface CHIResource {
//
//        @Get
//        public CHIData fetch_assignments(boolean basic);
//
//        @Get
//        public CHIData fetch_assignments(boolean basic, int[] assignmentids);
//
//    }
//
//    public static CHIResource getCHIResource(String token) {
//        Engine.getInstance().getRegisteredConverters().add(new GsonConverter());
//        ClientResource cr = new ClientResource(moodle_url + webservice_script);
//        cr.addQueryParameter("wstoken", token);
//        //cr.addQueryParameter(token, token)
//        cr.addQueryParameter("wsfunction", fetch_assign_func);
//        cr.addQueryParameter("moodlewsrestformat", "json");
//        return cr.wrap(CHIResource.class);
//    }
//
//
//    public static CHIResource getAssign(String token) {
//        return getCHIResource(token);
//    }
    
///**
//     *
//     * @param script
//     * @param params
//     * @return
//     */
//    private static <T> T aJSONRequest(String script, String[][] params, Class<T> classtype) {
//
//        //build url ...
//        StringBuilder sb = new StringBuilder();
//        sb.append(moodle_url).append(script).append("?moodlewsrestformat=json");
//        try {
//            for (String[] param : params) {
//                sb.append('&').append(param[0]).append('=').append(URLEncoder.encode(param[1], "UTF-8"));
//            }
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
////        System.out.println("url is: " + sb.toString());
//
//        // make the resourse
//        ClientResource cr = new ClientResource(sb.toString());
//        Reader reader = null;
//        T out = null;
//        try {
//            reader = cr.get(MediaType.APPLICATION_JSON).getReader();
////            readAndPrint(reader);
//            Gson gson = new Gson();
//            out = gson.fromJson(reader, classtype);
//        } catch (IOException ex) {
//            Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            // return/throw error here?
//        }
//        return out;
//    }
//}
///**
//     * get a single
//     *
//     * Download a file from the Moodle server
//     *
//     * @param token the token that uniquely identifies the user and their PW
//     * @param fileurl the URL of the file (includes the Moodle server address),
//     * as given by {@link #getAssignmentFileURLs(java.lang.String, int)} method
//     * @param pathname the file path (folder path/file name)
//     * @param rootSavePath the root path to save the downloaded files into
//     * @param placeUnderID places the downloaded files under a folder specified
//     * by the submission id
//     * @return an instance of the downloaded file
//     */
//    public static File getFile(String token, String fileurl, String savePath, boolean placeUnderID) {
//        ClientResource cr = new ClientResource(fileurl);
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
//        File fileOut = null;
//        try {
//            // read this file into InputStream
//            inputStream = cr.get(MediaType.APPLICATION_ZIP).getStream();
//            // write the inputStream to a FileOutputStream
//            fileOut = new File(savePath);
//            fileOut.getParentFile().mkdirs();
//            outputStream = new FileOutputStream(fileOut);
//
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            while ((read = inputStream.read(bytes)) != -1) {
//                outputStream.write(bytes, 0, read);
//            }
//            System.out.println("file successfully downloaded");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (outputStream != null) {
//                try {
//                    // outputStream.flush();
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//        return fileOut;
//    }
//
//    /**
//     * get a single file from the Moodle server
//     *
//     * @param token the token that uniquely identifies the user and their PW
//     * @param fileurl the URL of the file (includes the Moodle server address),
//     * as given by {@link #getAssignmentFileURLs(java.lang.String, int)} method
//     * @param zipFile
//     *
//     * @return the status of the
//     */
//    public static int sendFile(String token, String fileurl, File zipFile) throws IOException {
//
//        //build url ...
////        StringBuilder sb = new StringBuilder();
////        sb.append(moodle_url).append(script).append("?").append("moodlewsrestformat=json");
////        try {
////            for (String[] param : params) {
////                sb.append('&').append(param[0]).append('=').append(URLEncoder.encode(param[1], "UTF-8"));
////            }
////        } catch (UnsupportedEncodingException ex) {
////            Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
////        }
//        // create the client
//        ClientResource cr = new ClientResource(fileurl);
//        Reader reader = cr.post(zipFile, MediaType.APPLICATION_ZIP).getReader();
////        
////        
////        ClientConfig clientConfig = new DefaultClientConfig();
////        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
////        Client client = Client.create(clientConfig);
////
////        try {
////            InputStream fileInStream = new FileInputStream(zipFile.getName());
////
////            WebResource aService = client.resource(fileurl);
////            aService = aService.queryParam("wstoken", token);
////            aService = aService.queryParam("moodlewsrestformat", "json");
////
////            String sContentDisposition = "attachment; filename=\"" + zipFile.getName() + "\"";
////
////            ClientResponse response = aService.type(MediaType.APPLICATION_OCTET_STREAM)
////                    .header("Content-Disposition", sContentDisposition)
////                    .post(ClientResponse.class, fileInStream);
////
////            fileInStream.close();
////            // the response will not be instant ... wait a while & buffer
////            response.bufferEntity();
////            String x = response.getEntity(String.class);
////            System.out.println(x);
////            return response.getStatus();
////        } catch (FileNotFoundException ex) {
////            Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
////        } catch (IOException ex) {
////            Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
////        }
//        return 0;
//    }
//    public static String arrayToString(int[] array) {
//        StringBuilder sb = new StringBuilder();
//        sb.append('(').append(array[0]);
//        for (int i = 1; i < array.length; i++) {
//            sb.append(',').append(array[i]);
//        }
//        return sb.append(')').toString();
//    }


//        private boolean copyToFileArea(File source, char type) {
//            System.out.println("bf ddddddddddddddd " + baseFolder);
//            File target = new File(baseFolder + "t/" + (gradeonly ? "g/" : "t/") + basePath + type + '/' + source.getName());
//            //System.out.println("copying to " + target.getPath());
//            //System.out.println(target.getPath());
//            target.mkdirs();
//            try {
//                Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            } catch (IOException ex) {
//                Logger.getLogger(DataClasses.class.getName()).log(Level.SEVERE, null, ex);
//                return false;
//            }
//            return true;
//        }
//{"courses":[{"id":2,"shortname":"protopic","codehandins":[{"id":12,"contextid":39,"assignname":"apples","intro":"<p>fish description<br><\/p>","duedate":"1424361600","funcpercent":100,"spectestonly":false,"mustattemptcompile":true,"proglang":"java","proglangid":3,"checkpoints":[{"id":254,"name":"","description":"a cp desc","ordering":0,"marks":5,"tests":[{"id":432,"description":null,"gradeonly":false,"runtimeargs":null,"ioastext":false,"input":0,"output":0,"outputerr":0,"retval":null,"ordering":5,"marks":2}]},{"id":255,"name":"","description":"cp 2 desk","ordering":1,"marks":7,"tests":[]}]}]}]}
//{
//  "courses": [
//    {
//      "id": 2,
//      "shortname": "protopic",
//      "codehandins": [
//        {
//          "id": 12,
//          "contextid": 39,
//          "assignname": "apples",
//          "intro": "\u003cp\u003efish description\u003cbr\u003e\u003c/p\u003e",
//          "duedate": "1424361600",
//          "funcpercent": 100,
//          "spectestonly": false,
//          "mustattemptcompile": true,
//          "proglang": "java",
//          "proglangid": 3,
//          "checkpoints": [
//            {
//              "id": 254,
//              "name": "",
//              "description": "a cp desc",
//              "ordering": 0,
//              "marks": 5,
//              "tests": [
//                {
//                  "id": 432,
//                  "gradeonly": false,
//                  "ioastext": false,
//                  "input": 0,
//                  "output": 0,
//                  "outputerr": 0,
//                  "ordering": 5,
//                  "marks": 2
//                }
//              ]
//            },
//            {
//              "id": 255,
//              "name": "",
//              "description": "cp 2 desk",
//              "ordering": 1,
//              "marks": 7,
//              "tests": []
//            }
//          ]
//        }
//      ]
//    }
//  ]
//}

