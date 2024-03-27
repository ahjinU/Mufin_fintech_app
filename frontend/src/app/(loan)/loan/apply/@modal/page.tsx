import { useRouter } from 'next/navigation';
import AlertConfirm from '@/components/AlertConfirmModal/AlertConfirmModal';
import { AgreeBottomSheet, BottomSheet } from '@/components';

export default function Modal() {
  const router = useRouter();

  return (
    <div
      className="absolute top-0 left-0 size-full bg-custom-black-with-opacity
    flex justify-center"
    >
      hi
      <AgreeBottomSheet
        title={'대출 신청 동의'}
        notice={'대출 신청하려면 아래 약관 동의가 필요해요'}
        conditions={['개인 정보 수집 및 이용 동의', '대출 신청 동의']}
        isOpen={true}
      />
    </div>
  );
}
