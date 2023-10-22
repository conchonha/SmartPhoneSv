package edu.abcd.smartphone.Screen.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import edu.abcd.smartphone.Adapter.CategoryAdapter;
import edu.abcd.smartphone.Adapter.ProductAdapter;
import edu.abcd.smartphone.Model.Product;
import edu.abcd.smartphone.R;
import edu.abcd.smartphone.data_source.remote.DataServiceClient;
import edu.abcd.smartphone.data_source.remote.response.CategoryRespose;
import edu.abcd.smartphone.data_source.remote.response.ProductRespose;
import edu.abcd.smartphone.databinding.ActivityMainBinding;
import edu.abcd.smartphone.presentation.HomeViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    public DataServiceClient client;
    private static final String TAG = "MainActivity";
    private ProductAdapter adapterPupolar;
    private CategoryAdapter adapterCategory;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initVMCategory();
        initVMProduct();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
//            cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
    }

    private void initRvCategory(List<CategoryRespose> categoryResposes) {
        ArrayList<CategoryRespose> listCategory = new ArrayList<CategoryRespose>(categoryResposes);
        binding.rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterCategory = new CategoryAdapter(listCategory);
        binding.rvCategories.setAdapter(adapterCategory);
    }

    private void initRvProduct(List<ProductRespose> productResposes) {
//        ArrayList<Product> items = new ArrayList<>();
//        items.add(new Product("iPhone 15 Pro Max 512GB", "Với kích thước màn hình 6,7 inch giống iPhone 14 Pro Max nhưng iPhone 15 Pro Max đánh dấu sự xuất hiện đầu tiên của chất liệu Titanium trên dòng sản phẩm iPhone. Đây là một loại hợp kim cao cấp thường được sử dụng trong công nghiệp vũ trụ, nổi bật với tỷ lệ tối ưu giữa độ bền và trọng lượng. Điều này tạo nên những chiếc iPhone Pro Max nhẹ nhất từ trước đến nay." +
//                "Phiên bản này có bề mặt nhám tinh tế, các cạnh được bo tròn mềm mại và viền máy mỏng đáng kinh ngạc.Sự kết hợp giữa Titan và kính mặt lưng bền bỉ nhất trong ngành làm cho dòng sản phẩm Pro này trở nên vô cùng bền bỉ và mạnh mẽ.Mặt trước của điện thoại cũng được bảo vệ bằng Ceramic Shield hàng đầu, đảm bảo sự an toàn khi sử dụng.Sử dụng quy trình nhiệt cơ tiên tiến, lớp vỏ Titanium được tạo ra bằng cách liên kết với một cấu trúc phụ làm từ 100 % nhôm tái chế.Hai kim loại này được kết hợp một cách mạnh mẽ thông qua quá trình khuếch tán ở trạng thái rắn.Khung nhôm cũng giúp tản nhiệt hiệu quả và cho phép thay đổi mặt lưng kính một cách dễ dàng.",
//                "item_4",
//                15,
//                4,
//                500));
//        items.add(new Product("Smart Watch", "Immerse yourself in a world of vibrant visuals and\n" +
//                " immersive sound with the VisionX Pro LED TV.\n" +
//                " Its cutting-edge LED technology brings every\n" +
//                " scene to life with striking clarity and rich colors.\n" +
//                " With seamless integration and a sleek, modern\n" +
//                " design, the VisionX Pro is not just a TV, but a\n" +
//                " centerpiece for your entertainment space.\n" +
//                "With its sleek, modern design, the VisionX Pro is\n" +
//                " not just a TV, but a centerpiece for your \n" +
//                "entertainment space. The ultra-slim bezel and\n" +
//                " premium finish blend seamlessly with any decor", "item_2", 10, 4.5, 450));
//        items.add(new Product("IPhone 14", "Thiết kế mới với Dynamic Island\n" +
//                "Sau 5 năm, Apple đã nói lời tạm biệt với thiết kế màn hình “tai thỏ” để giới thiệu notch Dynamic Island - đây là một sự thay đổi ngoạn mục. Khu vực này sẽ tùy biến thú vị làm nổi bật khi có cuộc gọi đến, các thông báo của ứng dụng, âm nhạc và hơn thế nữa", "item_3", 15, 4.3, 800));
//        items.add(new Product("iPhone 12 Pro Max", "iPhone 12 Pro Max có màn hình Super Retina XDR 6.7 inch với độ phân giải 2778 x 1284 pixel, tương đương với 458 ppi. Thiết kế tổng thể của iPhone 12 Pro Max tập trung vào các cạnh phẳng tương tự như iPad Pro cũng như iPhone 4/iPhone 5. Phía trước vẫn là phần “tai thỏ” chứa Face ID giống những mẫu iPhone gần đây.\n" +
//                "\n" +
//                "Chất liệu thiết kế của điện thoại iPhone 12 phiên bản Pro Max cũng được làm từ “thép không gỉ dùng trong phẫu thuật”, với lớp ngoài sáng bóng bắt mắt, mang đến cảm giác cầm nắm chắc chắn và cứng cáp hơn. Cả hai thiết bị đều cho khả năng chống nước IP68 ở độ sâu tối đa 6 mét trong tối đa 30 phút.", "item_1", 18, 4.0, 1500));

        ArrayList<ProductRespose> listProduct = new ArrayList<ProductRespose>(productResposes);
        binding.view1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPupolar = new ProductAdapter(listProduct);
        binding.view1.setAdapter(adapterPupolar);
    }

    public void initVMCategory() {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getLiveDataCategory().observe(this, new Observer<List<CategoryRespose>>() {
            @Override
            public void onChanged(List<CategoryRespose> categoryResposes) {
                if (categoryResposes != null) {
                    initRvCategory(categoryResposes);
                    notificationLogin("data "+categoryResposes);
                } else {
                    notificationLogin("Check lai email va pass!");
                }
            }
        });
        homeViewModel.categoryAPI();
    }

    public void initVMProduct() {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getLiveDataProduct().observe(this, new Observer<List<ProductRespose>>() {
            @Override
            public void onChanged(List<ProductRespose> productResposes) {
                if (productResposes != null) {
                    initRvProduct(productResposes);
                    notificationLogin("data "+productResposes);
                } else {
                    notificationLogin("Check lai email va pass!");
                }
            }
        });
        homeViewModel.categoryAPI();
    }

    public void notificationLogin(String notification){
        Toast.makeText(MainActivity.this, notification, Toast.LENGTH_SHORT).show();
    }
}