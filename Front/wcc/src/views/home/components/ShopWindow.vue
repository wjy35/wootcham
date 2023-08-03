<template>
  <div class="content-window shadow">
    <div class="content">

      <div class="filter-bar">
          <div class="radio-inputs">
            <label class="radio">
              <input type="radio" name="radio" checked="">
              <span class="name">뱃지</span>
            </label>
            <label class="radio">
              <input type="radio" name="radio">
              <span class="name">프레임</span>
            </label>
                
            <label class="radio">
              <input type="radio" name="radio">
              <span class="name">아이콘</span>
            </label>
          </div>

          <input type="text" class="search" placeholder="Search..." />
      </div>
      
      <div class="products">
        <div class="card">
        <div class="card__subtitle">상품</div>
        <div class="card__wrapper">
        <div class="card__title">1000 포인트</div>
        <div class="card__icon"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 256 256" style="user-select: none; width: 100%; height: 100%; display: inline-block; fill: rgb(224, 223, 220); flex-shrink: 0; cursor: auto;" color="rgb(224, 223, 220)"><g color="rgb(224, 223, 220)"><circle cx="128" cy="128" r="96" opacity="0.2"></circle><circle cx="128" cy="128" r="96" fill="none" stroke="rgb(224, 223, 220)" stroke-miterlimit="10" stroke-width="16"></circle><polyline points="134.1 161.9 168 128 134.1 94.1" fill="none" stroke="rgb(224, 223, 220)" stroke-linecap="round" stroke-linejoin="round" stroke-width="16"></polyline><line x1="88" y1="128" x2="168" y2="128" fill="none" stroke="rgb(224, 223, 220)" stroke-linecap="round" stroke-linejoin="round" stroke-width="16"></line></g></svg></div>
    </div>
</div>
      
      </div>

    </div>
  </div>
</template>

<style scoped>
.content-window {
  background-color: #DEDEDE;
}

.content-window::before {
  content: '';
  position: absolute;
  top: 63%;
  left: -28px;
  width: 50px; 
  height: 50px; 
  background-image: url('@/assets/images/indicator.png');
  background-size: contain; 
  background-repeat: no-repeat; 
  background-color: transparent;
}

.filter-bar {
  display: flex;
  width: 500px;
  margin: 0 auto;
}

.radio-inputs {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  border-radius: 0.5rem;
  background-color: #EEE;
  box-sizing: border-box;
  box-shadow: 0 0 0px 1px rgba(0, 0, 0, 0.06);
  padding: 0.25rem;
  margin: 1rem;
  width: 300px;
  font-size: 14px;
}
.radio-inputs .radio {
  flex: 1 1 auto;
  text-align: center;
}
.radio-inputs .radio input {
  display: none;
}
.radio-inputs .radio .name {
  display: flex;
  cursor: pointer;
  align-items: center;
  justify-content: center;
  border-radius: 0.5rem;
  border: none;
  padding: .5rem 0;
  color: rgba(51, 65, 85, 1);
  transition: all .15s ease-in-out;
}
.radio-inputs .radio input:checked + .name {
  background-color: #fff;
  font-weight: 600;
}

.search {
  padding: 5px 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.products {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.card {
  --main-color: #323232;
  --sub-color: #5b5b5b;
  --bg-color: #c7c7c7;
  --accent-color: #242C88;
  position: relative;
  width: 270px;
  padding: 25px;
  border-radius: 25px;
  display: flex;
  flex-direction: column;
  gap: 5px;
  background-color: var(--bg-color);
  transition: all 0.2s;
  cursor: pointer;
  box-shadow: 0px 0px 5px -1.5px #000;
}

.card::before {
  content: '';
  width: 99%;
  height: 99%;
  background: var(--sub-color);
  position: absolute;
  z-index: -1;
  top: 1px;
  left: 1px;
  border-radius: 25px;
  transition: all 0.3s;
}

.card__wrapper {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.card__img {
  width: 100%;
}

.card__title {
  color: var(--main-color);
  font-weight: 900;
  font-size: 25px;
}

.card__subtitle {
  color: var(--sub-color);
  font-weight: 600;
  font-size: 20px;
}

.card__icon {
  width: 40px;
  height: 40px;
  transform: rotate(-45deg);
  transition: all 0.3s;
}

.card__icon svg {
  width: 100%;
  height: 100%;
}

.card__icon g {
  fill: var(--main-color);
}

.card__icon circle, polyline, line {
  stroke: var(--main-color);
}

.card:hover .card__icon {
  transform: rotate(0);
}

.card:hover .card__icon circle, .card:hover .card__icon polyline, .card:hover .card__icon line {
  stroke: var(--accent-color);
}

.card:hover .card__icon g {
  fill: var(--accent-color);
}

.card:hover {
  box-shadow: 0px 0px 20px -5px #000;
}

.card:hover::before {
  transform: rotate(10deg);
  box-shadow: 0px 0px 20px -5px #000;
}
</style>