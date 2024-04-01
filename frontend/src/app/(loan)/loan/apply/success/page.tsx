'use client';

import Complete from '@/app/_component/Complete';

export default function CompleteApplyLoan() {
  return (
    <section>
      <Complete
        title="대출 신청하기 완료!"
        content="부모님께 대출 신청을 완료했어요.
        이유가 합리적이었다면 대출에 성공할 수도,
        비합리적이었다면 대출에 실패할 수도 있어요."
        navText="홈으로 가기"
        link="/"
      />
    </section>
  );
}
