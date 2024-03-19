import type { Meta, StoryObj } from '@storybook/react';

import ImageUploadBox from './ImageUploadBox';

const meta = {
  title: 'Common/ImageUploadBox',
  component: ImageUploadBox,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    text: {
      description: '버튼의 텍스트입니다.',
    },
  },
} satisfies Meta<typeof ImageUploadBox>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    text: '계좌번호 사진 업로드하기',
  },
};
