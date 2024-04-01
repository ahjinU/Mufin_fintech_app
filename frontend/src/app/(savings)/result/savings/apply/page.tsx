'use client';

import Complete from '@/app/_component/Complete';

export default function CompleteApplySavings() {
  return (
    <section>
      <Complete
        title="적금 가입하기 완료!"
        content="적금 가입을 완료했어요. 이제 자유롭게 정한 금액을 납부하고, 일정 기간이 지나면 이자와 함께 돌려 받을 수 있답니다."
        navText="바로 이체하러 가기"
        link="/savings/mine"
      />
    </section>
  );
}
