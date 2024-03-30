'use client';

import Complete from '@/app/_component/Complete';

export default function CompleteApplySavings() {
  return (
    <section>
      <Complete
        title="적금 상품 만들기 완료!"
        content="적금 상품을 만들었어요. 이제 아이가 신청하기만을 기다리면 됩니다!."
        navText="적금 상품 목록 보러가기"
        link="/savings/list"
      />
    </section>
  );
}
