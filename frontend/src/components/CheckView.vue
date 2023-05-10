<template>

    <v-data-table
        :headers="headers"
        :items="check"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'CheckView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "status", value: "status" },
                { text: "orderId", value: "orderId" },
            ],
            check : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/checks'))

            temp.data._embedded.checks.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.check = temp.data._embedded.checks;
        },
        methods: {
        }
    }
</script>

