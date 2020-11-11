@extends('layouts.default')
@section('noi-dung')

<section class="wrapper">
    <div class="table-agile-info">
        <table class="table" ui-jq="footable" ui-options='{ "paging": { "enabled": true }, "filtering": { "enabled": true }, "sorting": { "enabled": true }}'>
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Tên phim</th>
                    <th scope="col">Đạo diễn</th>
                    <th scope="col">Thời lượng</th>
                    <th scope="col">Quốc gia</th>
                    <th scope="col">Thể loại</th>
                </tr>
            </thead>
            <tbody>
                @for($i=0;$i<5;$i++)
                    <tr>
                        <th scope="row">{{ $i+1 }}</th>
                        <td>Film</td>
                        <td>Otto</td>
                        <td>90p</td>
                        <td>Mỹ</td>
                        <td>Hành động</td>
                    </tr>
                @endfor
            </tbody>
        </table>
    </div>
</section>

<!-- footer -->
{{-- @include('includes.footer') --}}
<!-- / footer -->

@endsection