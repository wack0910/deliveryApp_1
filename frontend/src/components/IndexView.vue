<template>

    <v-data-table
        :headers="headers"
        :items="index"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'IndexView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            index : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/indices'))

            temp.data._embedded.indices.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.index = temp.data._embedded.indices;
        },
        methods: {
        }
    }
</script>

