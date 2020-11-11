@extends('layouts.default')
@section('noi-dung')

<div class="log-w3">
    <div class="w3layouts-main">
        <h2>Sign In Now</h2>
        <form action="#" method="post">
            <input type="email" class="ggg" name="Email" placeholder="E-MAIL" required="">
            <input type="password" class="ggg" name="Password" placeholder="PASSWORD" required="">
            <span><input type="checkbox" />Remember Me</span>
            <h6><a href="#">Forgot Password?</a></h6>
            <div class="clearfix"></div>
            <input type="submit" value="Sign In" name="login">
        </form>
        <p>Don't Have an Account ?<a href="registration.html">Create an account</a></p>
    </div>
</div>

<!-- footer -->
@include('includes.footer')
    <!-- / footer -->

@endsection
