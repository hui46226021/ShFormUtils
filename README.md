# ShFormUtils
android表单映射框架
#Download

Download the latest JAR or grab via Maven:
~~~xml
<dependency>
  <groupId>com.sh.shformutils</groupId>
  <artifactId>shform</artifactId>
  <version>2.0.0</version>
   <type>pom</type>
</dependency>
~~~
or Gradle:
~~~gradle
compile 'com.sh.shformutils:shform:2.0.0'
~~~
#功能
* 表单对象映射

   * 1.页面实现 FromCheckInterface 接口
   * 2.开启表单注入 FromInit.injection(this);
   ~~~java
         @Override
           protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_main);
               nameEdit = (EditText) findViewById(R.id.name);
               phoneEdit = (EditText) findViewById(R.id.phone);
               professionEdit = (EditText) findViewById(R.id.profession);
               spinner = (Spinner) findViewById(R.id.spinner);
               married = (CheckBox) findViewById(R.id.married);
               party = (CheckBox) findViewById(R.id.party);
               /**
                * 初始化表单注入  要在 所有控件初始化成功后 调用
                */
               FromInit.injection(this);
           }
   ~~~
   * 3.在 onDestroy 里 注销 表单
  ~~~java
           @Override
           protected void onDestroy() {
           /**
            * 注销表单
            */
           FromInit.deleteInjection(this);
           super.onDestroy();
           }
  ~~~
   * 4.通过 @FromInjection 绑定控件
  ~~~java
         @FromInjection(name = "name", message = "名字")
         EditText nameEdit;
         @FromCheck(type = CheckType.Phone)
         @FromInjection(name = "phone", message = "电话")
         EditText phoneEdit;
         @FromInjection(name = "profession", message = "公司-职业")
         EditText professionEdit;
         @FromInjection(name = "workingLife", message = "工作时间")
         Spinner spinner;
         @FromInjection(name = "married")
         CheckBox married;
   ~~~
   * 4.调用映射方法
   ~~~java
        /**
          * 表单自动生成对象
          */
         UserModel userModel = FromUtls.fromToObjectAndCheck(this,UserModel.class);
        /**
          * 实体对象 映射到表单 返回true 表示映射成功
          */
         FromUtls.objectToFrom(this,userModel);
  ~~~
![](1.gif)
*  表单参数检查
    * 1.使用 @FromCheck 实现表单验证
~~~java
   //type 是 验证类型枚举   默认 是自定义验证
    @FromCheck(type = CheckType.Phone)
    @FromInjection(name = "phone", message = "电话")
    EditText phoneEdit;
    @FromCheck
    @FromInjection(name = "profession", message = "公司-职业")
    EditText professionEdit;

        /**
         * 通过 实现 FromCheckInterface 接口的 fromCheck
         * 自定定义 表单检查 默认要返回true
         * @param v
         * @return
         */
        @Override
        public boolean fromCheck(View v) {
            switch (v.getId()){
                case R.id.profession:
                    if(!(professionEdit.getText()+"").contains("-")){
                        Toast.makeText(this,"职业格式不正确",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    break;
            }
            return true;
        }

        /**
         * 表单检查 为空回调
         * @param v
         * @param message
         */
        @Override
        public void fromCheckParamCall(View v, String message) {
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }
        /**
         * 表单检查 不合法回调
         * @param v
         * @param message
         */
        @Override
        public void fromCheckNullCall(View v, String message) {
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }
~~~


![](2.gif)