<script setup>


import { ref, onMounted, watch } from 'vue';
import axios from 'axios';


// 상태 변수 정의
const selectedCity = ref(''); // 선택된 값
const cityOptions = ref([]); // 드롭다운에 표시될 옵션들
const selectedDistrict = ref('');
const districtOptions = ref([]);


// API에서 데이터를 가져오는 함수
const fetchCityOptions = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/dropdown/cityUnique');
    cityOptions.value = response.data; // API에서 받은 데이터를 리스트에 저장
  } catch (error) {
    console.error('Error fetching city options:', error);
  }
};

const fetchDistrictOptions = async () => {
    try{
        console.log(`http://localhost:8080/api/dropdown/districtUnique?cityName=${selectedCity.value}`)
        const response = await axios.get(`http://localhost:8080/api/dropdown/districtUnique?cityName=${selectedCity.value}`);
        districtOptions.value = response.data;

    } catch(error){
        console.log('Error fetching district options:', error);
    }
}

// 컴포넌트가 마운트될 때 데이터를 가져옴
onMounted(() => {
  fetchCityOptions();
});

// selectedCity가 변경될 때 구군 데이터를 가져옴
watch(selectedCity, () => {
  fetchDistrictOptions();  // 도시가 변경될 때 구군 리스트를 다시 가져옴
});

</script>

<template>
    <div>
    <!-- 셀렉트 박스와 데이터 바인딩 -->
    <select v-model="selectedCity">
      <option v-for="city in cityOptions" :key="city" :value="city">
        {{ city }}
      </option>
    </select>

    <!-- 선택된 값 출력 -->
    <p>Selected city: {{ selectedCity }}</p>
  </div>
  
  <br>

  <div>
    <select v-model="selectedDistrict">
        <option v-for="district in districtOptions" :key="district" :value="district">
            {{  district }}
        </option>
    </select>

    <p>Selected district: {{ selectedDistrict }}</p>
  </div>
</template>

<style>
</style>