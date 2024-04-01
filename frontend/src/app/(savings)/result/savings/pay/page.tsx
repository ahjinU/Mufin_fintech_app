'use client';

import Complete from '@/app/_component/Complete';

export default function CompletePaySavings() {
  return (
    <section>
      <Complete
        title="적금 납부 완료!"
        content="적금 납부가 성공적으로 끝났어요. 목표한 개월수를 채울 때까지 꾸준히 납부해서 이자를 얻어봐요 :)"
        navText="적금 또 가입하러 가기"
        link="/savings/apply"
      />
    </section>
  );
}
