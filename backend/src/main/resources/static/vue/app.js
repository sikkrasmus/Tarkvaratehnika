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
        register: function() {
            axios({
                method: 'post',
                url: 'register',
                data: {email: this.reg_email, password: this.reg_password}
            }).then(function (response) {
                // document.location.replace("/");
            });
        },
        login: function () {
            axios({
                method: 'post',
                url: 'login',
                data: {email: this.log_email, password: this.log_password}
            }).then(function (response) {
                document.location.replace("/home")
            })
        }
    },

    mounted() {
        axios.get('/').then(response =>
            this.coin_data = response.data);
        // console.log(this.coin_data)
    },

    computed: {
        sorted_coins: function () {
            return _.orderBy(this.coin_data, ['marketCap'], ['desc'])
        }
    }


});
