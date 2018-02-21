var app = new Vue({
    el: '#enter-email',
    data: {
        email: ""
    },

    methods: {
        register() {

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