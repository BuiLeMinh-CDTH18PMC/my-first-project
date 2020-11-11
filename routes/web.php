<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('pages.trang-chu');
});

// Route::get('dang-nhap', function () {
//     return view('login');
// })->name('dang-nhap');

Auth::routes();

Route::group(['prefix'=>'phim'], function () {
    Route::get('danh-sach-phim', function () {
        return view('pages.danh-sach-phim');
    })->name('danh-sach-phim');

    Route::get('them-phim', function () {
        return view('pages.them-phim');
    })->name('them-phim');

    Route::get('cap-nhat-phim', function () {
        return view('pages.cap-nhat-phim');
    })->name('cap-nhat-phim');
});

Route::group(['prefix'=>'rap-phim'], function () {
    Route::get('danh-sach-rap', function () {
        return view('pages.danh-sach-rap');
    })->name('danh-sach-rap');
});

Route::group(['prefix'=>'thanh-vien'], function () {
    Route::get('danh-sach-thanh-vien', function () {
        return view('pages.danh-sach-thanh-vien');
    })->name('danh-sach-thanh-vien');
});


// Route::get('/home', 'HomeController@index')->name('home');
