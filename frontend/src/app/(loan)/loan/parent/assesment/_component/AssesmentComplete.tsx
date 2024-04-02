import Lottie from 'react-lottie-player';
import lottieJson from '../../../../../../../public/lotties/check.json';

export default function Complete(isApproved: { isApproved: boolean }) {
  return (
    <div className="flex flex-col items-center gap-[4rem]">
      <div className="mt-[4rem] flex flex-col gap-[1rem] text-custom-black">
        <p className="custom-bold-text">
          {isApproved ? '대출 수락 완료!' : '대출 거절 완료'}
        </p>
        <div>
          <p className="custom-medium-text">
            {isApproved
              ? '아이의 대출 요청을 수락하고, 송금했어요.'
              : '아이의 대출 요청을 거절했어요.'}
          </p>
          <p className="custom-medium-text">
            {isApproved
              ? '아이가 정해진 날짜에 잘 상환하는지 확인할 수 있습니다.'
              : '아이가 실망할 수도 있지만, 합리적인 이유와 함께 다시 대출을 진행할 수 있도록 독려해주세요.'}
          </p>
        </div>
      </div>
      <Lottie
        loop
        animationData={lottieJson}
        play
        style={{ width: 200, height: 200 }}
        className="self-center"
      />
    </div>
  );
}
