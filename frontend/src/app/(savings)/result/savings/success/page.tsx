'use client';

import Complete from '@/app/_component/Complete';

export default function CompletePaySavings() {
  return (
    <section>
      <Complete
        title="적금 만기까지 꾸준히 달려왔어요!"
        content="적금 만기 일자까지 꾸준히 납부하고, 이자까지 받았네요! 이번 경험으로 은행에서 실제 적금 상품을 들더라도 적금 만기까지 꾸준히 납부할 힘이 생겼어요 :)"
        navText="적금 또 가입하러 가기"
        link="/savings/apply"
      />
    </section>
  );
}
