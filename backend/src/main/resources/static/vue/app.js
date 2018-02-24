new Vue({
    el: '#app',
    data: {
        email: "",
        password: ""
    },


    methods: {
        register: function() {
            // console.log(this.email);
            // console.log(this.password);

            axios({
                method: 'post',
                url: 'register',
                data: {email: this.email, password: this.password}
            }).then(function (response) {
                document.location.replace("/");
            });
        }
    }


});
