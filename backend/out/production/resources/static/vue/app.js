Vue.use(axios);

new Vue({
    el: '#app',
    data: {
        reg_email: "",
        reg_password: "",
        log_email: "",
        log_password: "",

        coin_data: [],
    },

    methods: {
        login: function () {
            axios({
                method: 'post',
                url: 'login',
                data: {email: this.log_email, password: this.log_password}
            }).then(function (response) {
                document.location.replace("/home")
            })
        },
    },

    mounted() {
        axios.get('/').then(response => this.coin_data = response.data);
        console.log(this.coin_data)
    }


});
